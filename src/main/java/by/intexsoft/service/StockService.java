package by.intexsoft.service;

import by.intexsoft.model.Stock;

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
     *
     * @param id
     * @return {@link Stock}
     */
    Stock find(Integer id);

    /**
     * Find one first Stock by specialize
     *
     * @return {@link Stock}
     */
    Stock findBySpecialize(String specialize);

    /**
     * Find all {@link Stock}s in database
     *
     * @return list of {@link Stock} from database
     */
    List<Stock> findAll();

    /**
     * Update information about {@link Stock} in database
     *
     * @param object
     * @return {@link Stock} with changed data
     */
    Stock update(Stock object);

    /**
     * Delete {@link Stock} from database by identifier
     *
     * @param id
     */
    void delete(Integer id);
}

