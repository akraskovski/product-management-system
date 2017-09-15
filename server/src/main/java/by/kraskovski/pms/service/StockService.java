package by.kraskovski.pms.service;

import by.kraskovski.pms.controller.dto.ProductStockDto;
import by.kraskovski.pms.domain.model.Stock;

import java.util.List;

/**
 * Service for {@link Stock}
 */
public interface StockService {

    /**
     * Save {@link Stock} entity to database table.
     */
    Stock create(Stock object);

    /**
     * Find {@link Stock} in database by identifier.
     */
    Stock find(String id);

    /**
     * Find all products contains in stock.
     */
    List<ProductStockDto> findProducts(String id);

    /**
     * Add product to stock.
     */
    boolean addProduct(String stockId, String productId, int count);

    /**
     * Delete one (or more) product(s) from stock.
     */
    boolean deleteProduct(String stockId, String productId, int count);

    /**
     * Find all {@link Stock}s in database.
     */
    List<Stock> findAll();

    /**
     * Update information about {@link Stock} in database.
     */
    Stock update(Stock object);

    /**
     * Delete {@link Stock} from database by identifier.
     */
    void delete(String id);

    /**
     * Delete all stocks from the database
     */
    void deleteAll();
}

