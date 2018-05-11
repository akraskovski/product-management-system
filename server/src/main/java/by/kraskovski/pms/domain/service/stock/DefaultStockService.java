package by.kraskovski.pms.domain.service.stock;

import by.kraskovski.pms.domain.model.Product;
import by.kraskovski.pms.domain.model.ProductStock;
import by.kraskovski.pms.domain.model.Stock;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.domain.model.enums.AuthorityEnum;
import by.kraskovski.pms.domain.repository.StockRepository;
import by.kraskovski.pms.domain.service.ProductService;
import by.kraskovski.pms.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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

import static by.kraskovski.pms.domain.model.enums.AuthorityEnum.ROLE_ADMIN;
import static by.kraskovski.pms.domain.model.enums.AuthorityEnum.ROLE_STOCK_MANAGER;
import static by.kraskovski.pms.domain.model.enums.AuthorityEnum.ROLE_STORE_MANAGER;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultStockService implements StockService {

    private final StockRepository stockRepository;
    private final ProductService productService;
    private final ProductStockService productStockService;
    private final UserService userService;

    @Override
    public Stock create(final Stock object, final String managerId) {
        if (StringUtils.isNotEmpty(managerId)) {
            object.setManager(userService.find(managerId));
        }
        return create(object);
    }

    @Override
    public Stock create(final Stock object) {
        if (Objects.isNull(object.getManager())) {
            object.setManager(getDefaultManager());
        } else {
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
        if (count < 0) {
            throw new IllegalArgumentException("Count of products can't be less than 1");
        }

        final Stock stock = find(stockId);
        try {
            final ProductStock productStock = productStockService.findByStockIdAndProductId(stockId, productId);
            addExistingProductToStock(productStock, stock, count);
        } catch (EntityNotFoundException e) {
            addNewProductToStock(stock, productService.find(productId), count);
        }
    }

    @Override
    @Transactional
    public void deleteProduct(final String stockId, final String productId, final int count) {
        final ProductStock productStock = productStockService.findByStockIdAndProductId(stockId, productId);
        deleteProductFromProductStock(productStock, find(stockId), count);
    }

    @Override
    public List<Stock> findAll() {
        final User currentUser = userService.getCurrentUser();
        final AuthorityEnum authorityName = currentUser.getAuthority().getName();

        if (authorityName.equals(ROLE_ADMIN) || authorityName.equals(ROLE_STORE_MANAGER)) {
            return stockRepository.findAll();
        }

        return stockRepository.findAllByManager(currentUser);
    }

    @Override
    public Stock update(final Stock object, final String managerId) {
        if (StringUtils.isNotEmpty(managerId)) {
            object.setManager(userService.find(managerId));
        }
        return create(object);
    }

    @Override
    public Stock update(final Stock object) {
        final Stock oldStock = find(object.getId());

        if (!oldStock.getManager().equals(object.getManager())) {
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
        if (!userService.find(managerId).getAuthority().getName().equals(ROLE_STOCK_MANAGER)) {
            throw new AccessDeniedException("User should have stock manager role");
        }
    }

    private User getDefaultManager() {
        final User user = userService.getCurrentUser();
        checkForManagerAccess(user.getId());
        return user;
    }

    private void addExistingProductToStock(final ProductStock productStock, final Stock stock, final int count) {
        productStock.setProductsCount(productStock.getProductsCount() + count);
        stockRepository.save(stock);
    }

    private void addNewProductToStock(final Stock stock, final Product product, final int count) {
        stock.getProductStocks().add(new ProductStock(product, stock, count));
        stockRepository.save(stock);
    }

    private void deleteProductFromProductStock(final ProductStock productStock, final Stock stock, final int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Count of products to delete can't be less than 1");
        }

        if (productStock.getProductsCount() - count > 0) {
            productStock.setProductsCount(productStock.getProductsCount() - count);
            stockRepository.save(stock);
        } else {
            stock.getProductStocks().remove(productStock);
            stockRepository.save(stock);
        }
    }
}
