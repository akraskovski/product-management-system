package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.Cart;

import javax.management.InstanceAlreadyExistsException;

/**
 * Service for {@link Cart}
 */
public interface CartService {

    /**
     * Save {@link Cart} entity to database table
     */
    void create(String id) throws InstanceAlreadyExistsException;

    /**
     * Add product to cart.
     */
    boolean addProduct(String cartId, String productStockId, int count);

    /**
     * Delete one (or more) product(s) from cart.
     */
    boolean deleteProduct(String cartId, String productStockId, int count);

    /**
     * Find {@link Cart} in database by identifier
     */
    Cart find(String id);

    /**
     * Update information about {@link Cart} in database
     */
    Cart update(Cart object);

    /**
     * Delete {@link Cart} from database by identifier
     */
    void delete(String id);

    /**
     * Delete all {@link Cart}s from database
     */
    void deleteAll();
}

