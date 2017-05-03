package by.kraskovski.service;

import by.kraskovski.model.Product;

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
     */
    Product find(int id);

    /**
     * Find {@link Product}s by name
     */
    List<Product> findByName(String name);

    /**
     * Find {@link Product}s by type
     */
    List<Product> findByType(String type);

    /**
     * Find all {@link Product}s in database
     */
    List<Product> findAll();

    /**
     * Update information about {@link Product} in database
     */
    Product update(Product object);

    /**
     * Delete {@link Product} from database by identifier
     */
    void delete(int id);
}