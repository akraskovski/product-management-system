package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.model.Product;
import by.kraskovski.pms.model.ProductStock;
import by.kraskovski.pms.model.Stock;
import by.kraskovski.pms.repository.StockRepository;
import by.kraskovski.pms.service.ProductService;
import by.kraskovski.pms.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        Stock stock = stockRepository.findOne(id);
        final Map<Integer, Integer> products = new HashMap<>();
        stock.getProductStocks()
                .forEach(productStock -> products.put(productStock.getProduct().getId(), productStock.productsCount));
        return products;
    }

    @Override
    @Transactional
    public boolean addProduct(final int stockId, final int productId) {
        Stock stock = stockRepository.findOne(stockId);
        Product product = productService.find(productId);
        if (stock != null && product != null) {
            for (ProductStock productStock : stock.getProductStocks()) {
                if (productStock.getProduct().equals(product)) {
                    productStock.productsCount++;
                    stockRepository.save(stock);
                    return true;                                                        
                }
            }
            ProductStock addingProduct = new ProductStock();
            addingProduct.setProduct(product);
            addingProduct.setStock(stock);
            addingProduct.productsCount = 1;
            stock.getProductStocks().add(addingProduct);
            stockRepository.save(stock);
            return true;
        }
        return false;
    }

    @Override
    public Stock findBySpecialize(final String specialize) {
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
