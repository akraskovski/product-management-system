package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.domain.Product;
import by.kraskovski.pms.domain.ProductStock;
import by.kraskovski.pms.domain.Stock;
import by.kraskovski.pms.repository.StockRepository;
import by.kraskovski.pms.service.ProductService;
import by.kraskovski.pms.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final ProductService productService;

    @Autowired
    public StockServiceImpl(final StockRepository stockRepository, final ProductService productService) {
        this.stockRepository = stockRepository;
        this.productService = productService;
    }

    @Override
    public Stock create(final Stock object) {
        return stockRepository.save(object);
    }

    @Override
    public Stock find(final int id) {
        return stockRepository.findOne(id);
    }

    @Override
    public Map<Integer, Integer> findProducts(final int id) {
        final Stock stock = stockRepository.findOne(id);
        final Map<Integer, Integer> products = new HashMap<>();
        stock.getProductStocks()
                .forEach(productStock -> products.put(productStock.getProduct().getId(), productStock.getProductsCount()));
        return products;
    }

    @Override
    public boolean addProduct(final int stockId, final int productId, final int count) {
        final Stock stock = find(stockId);
        final Product product = productService.find(productId);

        if (stock == null || product == null) {
            return false;
        }

        for (ProductStock productStock : stock.getProductStocks()) {
            if (productStock.getProduct().equals(product)) {
                return addExistingProductToStock(productStock, stock, count);
            }
        }
        return addNewProductToStock(stock, product, count);
    }

    private boolean addExistingProductToStock(final ProductStock productStock, final Stock stock, final int count) {
        try {
            productStock.setProductsCount(productStock.getProductsCount() + count);
            stockRepository.save(stock);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    private boolean addNewProductToStock(final Stock stock, final Product product, final int count) {
        try {
            final ProductStock addingProduct = new ProductStock();
            addingProduct.setProduct(product);
            addingProduct.setStock(stock);
            addingProduct.setProductsCount(count);
            stock.getProductStocks().add(addingProduct);
            stockRepository.save(stock);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean deleteProduct(final int stockId, final int productId, final int count) {
        final Stock stock = find(stockId);
        final Product product = productService.find(productId);

        if (stock.getProductStocks() == null && product == null) {
            return false;
        }

        for (ProductStock productStock : stock.getProductStocks()) {
            if (productStock.getProduct().equals(product)) {
                return deleteProductFromProductStock(productStock, stock, count);
            }
        }
        return false;
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
    public List<Stock> findBySpecialize(final String specialize) {
        return stockRepository.findBySpecialize(specialize);
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
    public void delete(final int id) {
        stockRepository.delete(id);
    }
}
