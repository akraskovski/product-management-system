package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.Product;

import java.util.List;

/**
 * Service for {@link Product}
 */
public interface ProductService extends CRUDService<Product> {

    /**
     * Find {@link Product}s by name
     */
    List<Product> findByName(String name);

    /**
     * Find {@link Product}s by type
     */
    List<Product> findByType(String type);

    /**
     * Update information about {@link Product} in database
     */
    Product update(Product object);
}
