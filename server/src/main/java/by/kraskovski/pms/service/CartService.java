package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.model.Cart;

import javax.management.InstanceAlreadyExistsException;

/**
 * Service for {@link Cart}
 */
public interface CartService extends AbstractService<Cart> {

    /**
     * Save {@link Cart} entity to database table
     */
    void create(String id) throws InstanceAlreadyExistsException;

    /**
     * Add product to cart.
     */
    void addProduct(String cartId, String productStockId, int count);

    /**
     * Delete one (or more) product(s) from cart.
     */
    void deleteProduct(String cartId, String productStockId, int count);

    /**
     * Update information about {@link Cart} in database
     */
    Cart update(Cart object);

    @Override
    default Cart create(Cart object) {
        return new Cart();
    }
}

