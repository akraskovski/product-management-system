package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.domain.model.Product;
import by.kraskovski.pms.domain.model.ProductStock;
import by.kraskovski.pms.domain.model.Stock;
import by.kraskovski.pms.repository.StockRepository;
import by.kraskovski.pms.service.ProductService;
import by.kraskovski.pms.service.ProductStockService;
import by.kraskovski.pms.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ProductStockService productStockService;

    @Autowired
    public StockServiceImpl(final StockRepository stockRepository,
                            final ProductService productService,
                            final ProductStockService productStockService) {
        this.stockRepository = stockRepository;
        this.productService = productService;
        this.productStockService = productStockService;
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
        if (count < 1) {
            throw new IllegalArgumentException("The number of products can't be less than 1!");
        }
        productStock.setProductsCount(productStock.getProductsCount() + count);
        stockRepository.save(stock);
    }

    private void addNewProductToStock(final Stock stock, final Product product, final int count) {
        if (count < 1) {
            throw new IllegalArgumentException("The number of products can't be less than 1!");
        }
        stock.getProductStocks().add(new ProductStock(product, stock, count));
        stockRepository.save(stock);
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
