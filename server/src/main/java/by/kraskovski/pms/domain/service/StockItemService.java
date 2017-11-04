package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.Product;
import by.kraskovski.pms.domain.model.ProductStock;
import by.kraskovski.pms.domain.model.Stock;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.domain.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static by.kraskovski.pms.domain.model.enums.AuthorityEnum.ROLE_STOCK_MANAGER;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StockItemService implements StockService {

    private final StockRepository stockRepository;
    private final ProductService productService;
    private final ProductStockService productStockService;
    private final UserService userService;

    @Override
    public Stock create(final Stock object) {
        if (Objects.nonNull(object.getManager())) {
            checkForManagerAccess(object.getManager().getId());
        }
        return stockRepository.save(object);
    }

    @Override
    public Stock find(final String id) {
        return Optional.ofNullable(stockRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Stock with id: " + id + " not found in db!"));
    }

    @Override
    public Set<ProductStock> findProducts(final String id) {
        final Stock stock = find(id);
        if (CollectionUtils.isEmpty(stock.getProductStocks())) {
            return Collections.emptySet();
        }
        return stock.getProductStocks();
    }

    @Override
    @Transactional
    public void addProduct(final String stockId, final String productId, final int count) {
        final Stock stock = find(stockId);
        try {
            final ProductStock productStock = productStockService.findByStockIdAndProductId(stockId, productId);
            addExistingProductToStock(productStock, stock, count);
        } catch (EntityNotFoundException e) {
            addNewProductToStock(stock, productService.find(productId), count);
        }
    }

    private void addExistingProductToStock(final ProductStock productStock, final Stock stock, final int count) {
        verifyCount(count);
        productStock.setProductsCount(productStock.getProductsCount() + count);
        stockRepository.save(stock);
    }

    private void addNewProductToStock(final Stock stock, final Product product, final int count) {
        verifyCount(count);
        stock.getProductStocks().add(new ProductStock(product, stock, count));
        stockRepository.save(stock);
    }

    private void verifyCount(final int count) {
        if (count < 1) {
            throw new IllegalArgumentException("The number of products can't be less than 1!");
        }
    }

    @Override
    @Transactional
    public void deleteProduct(final String stockId, final String productId, final int count) {
        final ProductStock productStock = productStockService.findByStockIdAndProductId(stockId, productId);
        deleteProductFromProductStock(productStock, find(stockId), count);
    }

    private void deleteProductFromProductStock(final ProductStock productStock, final Stock stock, final int count) {
        if (productStock.getProductsCount() - count > 0) {
            productStock.setProductsCount(productStock.getProductsCount() - count);
            stockRepository.save(stock);
        } else {
            stock.getProductStocks().remove(productStock);
            stockRepository.save(stock);
        }
    }

    @Override
    public List<Stock> findManagerRelatedStocks(final String managerId) {
        checkForManagerAccess(managerId);
        return stockRepository.findByManagerId(managerId);
    }

    @Override
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public Stock update(final Stock object) {
        if (Objects.nonNull(object.getManager())) {
            checkForManagerAccess(object.getManager().getId());
        }
        return stockRepository.save(object);
    }

    @Override
    public void delete(final String id) {
        stockRepository.delete(find(id));
    }

    @Override
    public void deleteAll() {
        stockRepository.deleteAll();
    }

    private void checkForManagerAccess(final String managerId) {
        final User user = userService.find(managerId);
        if (user.getAuthorities().stream().noneMatch(auth -> auth.getName().equals(ROLE_STOCK_MANAGER))) {
            throw new AccessDeniedException("User should have stock manager role");
        }
    }
}
