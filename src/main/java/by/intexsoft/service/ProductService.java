package by.intexsoft.service;

import by.intexsoft.model.Product;

import java.util.List;

/**
 * Service for {@link Product}
 */
public interface ProductService {
    /**
     * Save {@link Product} entity to database table
     */
    Product create(Product object);

    /**
     * Find {@link Product} in database by identifier
     *
     * @param id
     * @return {@link Product}
     */
    Product find(Integer id);

    /**
     * Find one first Product by name
     *
     * @return {@link Product}
     */
    List<Product> findByName(String name);

    /**
     * Find all {@link Product}s in database
     *
     * @return list of {@link Product} from database
     */
    List<Product> findAll();

    /**
     * Update information about {@link Product} in database
     *
     * @param object
     * @return {@link Product} with changed data
     */
    Product update(Product object);

    /**
     * Delete {@link Product} from database by identifier
     *
     * @param id
     */
    void delete(Integer id);
}