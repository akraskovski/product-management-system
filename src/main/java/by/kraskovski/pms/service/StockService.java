package by.kraskovski.pms.service;

import by.kraskovski.pms.model.Stock;

import java.util.List;
import java.util.Map;

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
    Stock find(int id);

    /**
     * Find one first Stock by specialize.
     */
    Stock findBySpecialize(String specialize);

    /**
     * Find all products contains in stock.
     */
    Map<Integer, Integer> findProducts(int id);

    /**
     * Add product to stock.
     */
    void addProduct(int stockId, int productId);

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
    void delete(int id);
}

