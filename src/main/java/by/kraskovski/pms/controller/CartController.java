package by.kraskovski.pms.controller;

import by.kraskovski.pms.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Controller for the {@link by.kraskovski.pms.service.CartService}.
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CartController.class);
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Add productStock to user cart
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
}
