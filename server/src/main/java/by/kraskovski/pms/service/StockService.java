package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.model.ProductStock;
import by.kraskovski.pms.domain.model.Stock;

import java.util.List;
import java.util.Set;

/**
 * Service for {@link Stock}
 */
public interface StockService extends AbstractService<Stock> {

    /**
     * Find all products contains in stock.
     */
    Set<ProductStock> findProducts(String id);

    /**
     * Add product to stock.
     */
    void addProduct(String stockId, String productId, int count);

    /**
     * Delete one (or more) product(s) from stock.
     */
    void deleteProduct(String stockId, String productId, int count);
    /**
     * Update information about {@link Stock} in database.
     */
    Stock update(Stock object);
}
