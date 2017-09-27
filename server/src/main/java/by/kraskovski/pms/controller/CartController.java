package by.kraskovski.pms.controller;

import by.kraskovski.pms.controller.dto.CartDto;
import by.kraskovski.pms.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.management.InstanceAlreadyExistsException;

/**
 * Controller for the {@link by.kraskovski.pms.service.CartService}.
 */
@RestController
@RequestMapping("/cart")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CartController {

    private final CartService cartService;
    private final Mapper mapper;

    /**
     * Find cart by id.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity loadCartById(@PathVariable("id") final String id) {
        log.info("Start loadCartById: {}", id);
        return ResponseEntity.ok(mapper.map(cartService.find(id), CartDto.class));
    }

    /**
     * Create empty cart.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCart(@PathVariable("id") final String id) throws InstanceAlreadyExistsException {
        log.info("Start createCart for user with id: {}", id);
        cartService.create(id);
    }

    /**
     * Add productStock to user cart.
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addProductToCart(
            @RequestParam("cart_id") final String cartId,
            @RequestParam("ps_id") final String productStockId,
            @RequestParam(value = "count", required = false, defaultValue = "1") final int count) {
        log.info("start addProductStock: {} to cart: {} with count: {}", productStockId, cartId, count);
        cartService.addProduct(cartId, productStockId, count);
    }

    /**
     * Delete product from cart by id.
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductFromCart(
            @RequestParam("cart_id") final String cartId,
            @RequestParam("ps_id") final String productStockId,
            @RequestParam(value = "count", required = false, defaultValue = "1") final int count) {
        log.info("start deleteProductFromCart: {} from cart: {} with count: {}", productStockId, cartId, count);
        cartService.deleteProduct(cartId, productStockId, count);
    }

    /**
     * Delete cart by id.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCart(@PathVariable("id") final String id) {
        log.info("Start deleteCart: {}", id);
        cartService.delete(id);
    }
}
