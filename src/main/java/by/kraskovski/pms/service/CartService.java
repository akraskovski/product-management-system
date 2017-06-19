package by.kraskovski.pms.service;

import by.kraskovski.pms.model.Cart;

/**
 * Service for {@link Cart}
 */
public interface CartService {

    /**
     * Save {@link Cart} entity to database table
     */
    Cart create(Cart object);

    /**
     * Add product to cart.
     */
    boolean addProduct(int cartId, int productId, int count);

    /**
     * Delete one (or more) product(s) from cart.
     */
    boolean deleteProduct(int cartId, int productId, int count);

    /**
     * Find {@link Cart} in database by identifier
     */
    Cart find(int id);

    /**
     * Update information about {@link Cart} in database
     */
    Cart update(Cart object);

    /**
     * Delete {@link Cart} from database by identifier
     */
    void delete(int id);
}

