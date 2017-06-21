package by.kraskovski.pms.controller;

import by.kraskovski.pms.model.Cart;
import by.kraskovski.pms.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Controller for the {@link by.kraskovski.pms.service.CartService}.
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);
    private final CartService cartService;

    @Autowired
    public CartController(final CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Find cart by id.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity loadCartById(@PathVariable("id") final int id) {
        LOGGER.info("Start loadCartById: " + id);
        try {
            final Cart cart = cartService.find(id);
            Assert.notNull(cart, "Unable to find cart with id: " + id);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Create empty cart.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createCart(@RequestBody final Cart cart) {
        LOGGER.info("Start createCart");
        try {
            return new ResponseEntity<>(cartService.create(cart), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info("Error in createCart. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Add productStock to user cart.
     */
    @RequestMapping(value = "/{cart_id}/product_stock/{ps_id}", method = RequestMethod.PUT)
    public ResponseEntity addProductToCart(
            @PathVariable("cart_id") final int cartId,
            @PathVariable("ps_id") final int productStockId,
            @RequestParam(value = "count", required = false, defaultValue = "1") final int count) {
        LOGGER.info("start addProductStock: {} to cart: {} with count: {}", productStockId, cartId, count);
        return cartService.addProduct(cartId, productStockId, count)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Delete product from cart by id.
     */
    @RequestMapping(value = "/{cart_id}/product_stock/{ps_id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProductFromCart(
            @PathVariable("cart_id") final int cartId,
            @PathVariable("ps_id") final int productStockId,
            @RequestParam(value = "count", required = false, defaultValue = "1") final int count) {
        LOGGER.info("start deleteProductFromCart: {} from cart: {} with count: {}", productStockId, cartId, count);
        return cartService.deleteProduct(cartId, productStockId, count)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Delete cart by id.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCart(@PathVariable("id") final int id) {
        LOGGER.info("Start deleteCart");
        try {
            cartService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            LOGGER.info("Error in deleteCart. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
