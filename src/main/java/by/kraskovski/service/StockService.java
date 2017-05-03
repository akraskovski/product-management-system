package by.kraskovski.service;

import by.kraskovski.model.Stock;

import java.util.List;

/**
 * Service for {@link Stock}
 */
public interface StockService {

    /**
     * Save {@link Stock} entity to database table
     */
    Stock create(Stock object);

    /**
     * Find {@link Stock} in database by identifier
     */
    Stock find(int id);

    /**
     * Find one first Stock by specialize
     */
    Stock findBySpecialize(String specialize);

    /**
     * Find all {@link Stock}s in database
     */
    List<Stock> findAll();

    /**
     * Update information about {@link Stock} in database
     */
    Stock update(Stock object);

    /**
     * Delete {@link Stock} from database by identifier
     */
    void delete(int id);
}

