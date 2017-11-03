package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.ProductStock;
import by.kraskovski.pms.domain.model.Stock;

import java.util.List;
import java.util.Set;

/**
 * Service for {@link Stock}
 */
public interface StockService extends CRUDService<Stock> {

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
     * Find all stock, in which user set to manager.
     */
    List<Stock> findManagerRelatedStocks(String managerId);
}
