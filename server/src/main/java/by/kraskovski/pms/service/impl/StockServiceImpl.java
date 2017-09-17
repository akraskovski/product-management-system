package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.domain.model.Product;
import by.kraskovski.pms.domain.model.ProductStock;
import by.kraskovski.pms.domain.model.Stock;
import by.kraskovski.pms.repository.StockRepository;
import by.kraskovski.pms.service.ProductService;
import by.kraskovski.pms.service.StockService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final ProductService productService;
    private final Mapper mapper;

    @Autowired
    public StockServiceImpl(final StockRepository stockRepository,
                            final ProductService productService,
                            final Mapper mapper) {
        this.stockRepository = stockRepository;
        this.productService = productService;
        this.mapper = mapper;
    }

    @Override
    public Stock create(final Stock object) {
        return stockRepository.save(object);
    }

    @Override
    public Stock find(final String id) {
        return Optional.ofNullable(stockRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Stock with id: " + id + " not found in db!"));
    }

    @Override
    public Set<ProductStock> findProducts(final String id) {
        final Stock stock = stockRepository.findOne(id);
        if (CollectionUtils.isEmpty(stock.getProductStocks())) {
            return Collections.emptySet();
        }
        return stock.getProductStocks();
    }

    @Override
    @Transactional
    //TODO: refactor add and delete methods
    public void addProduct(final String stockId, final String productId, final int count) {
        final Stock stock = find(stockId);
        final Product product = productService.find(productId);

        for (ProductStock productStock : stock.getProductStocks()) {
            if (productStock.getProduct().equals(product)) {
                addExistingProductToStock(productStock, stock, count);
                return;
            }
        }
        addNewProductToStock(stock, product, count);
    }

    private void addExistingProductToStock(final ProductStock productStock, final Stock stock, final int count) {
        if (count < 1) {
            return;
            //throw exception
        }
        productStock.setProductsCount(productStock.getProductsCount() + count);
        stockRepository.save(stock);
    }

    private void addNewProductToStock(final Stock stock, final Product product, final int count) {
        if (count < 1) {
            return;
            //throw exception
        }
        stock.getProductStocks().add(new ProductStock(product, stock, count));
        stockRepository.save(stock);
    }

    @Override
    @Transactional
    public void deleteProduct(final String stockId, final String productId, final int count) {
        final Stock stock = find(stockId);
        final Product product = productService.find(productId);

        for (ProductStock productStock : stock.getProductStocks()) {
            if (productStock.getProduct().equals(product)) {
                deleteProductFromProductStock(productStock, stock, count);
                return;
            }
        }
    }

    private boolean deleteProductFromProductStock(final ProductStock productStock, final Stock stock, final int count) {
        try {
            if (productStock.getProductsCount() - count > 0) {
                productStock.setProductsCount(productStock.getProductsCount() - count);
                stockRepository.save(stock);
            } else {
                stock.getProductStocks().remove(productStock);
                stockRepository.save(stock);
            }
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public Stock update(final Stock object) {
        return stockRepository.save(object);
    }

    @Override
    public void delete(final String id) {
        stockRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        stockRepository.deleteAll();
    }
}
