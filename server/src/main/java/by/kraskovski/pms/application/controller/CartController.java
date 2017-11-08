package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.application.controller.dto.CartDto;
import by.kraskovski.pms.domain.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the {@link by.kraskovski.pms.domain.service.CartService}.
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
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CartDto loadCartById(@PathVariable final String id) {
        log.info("Start loadCartById: {}", id);
        return mapper.map(cartService.find(id), CartDto.class);
    }

    /**
     * Create empty cart.
     */
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCart(@PathVariable final String id) {
        log.info("Start createCart for user with id: {}", id);
        cartService.create(id);
    }

    /**
     * Add productStock to user cart.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addProductToCart(
            @RequestParam final String cartId,
            @RequestParam final String productStockId,
            @RequestParam(value = "count", required = false, defaultValue = "1") final int count) {
        log.info("start addProductStock: {} to cart: {} with count: {}", productStockId, cartId, count);
        cartService.addProduct(cartId, productStockId, count);
    }

    /**
     * Delete product from cart by id.
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductFromCart(
            @RequestParam final String cartId,
            @RequestParam final String productStockId,
            @RequestParam(value = "count", required = false, defaultValue = "1") final int count) {
        log.info("start deleteProductFromCart: {} from cart: {} with count: {}", productStockId, cartId, count);
        cartService.deleteProduct(cartId, productStockId, count);
    }

    /**
     * Delete cart by id.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCart(@PathVariable final String id) {
        log.info("Start deleteCart: {}", id);
        cartService.delete(id);
    }
}
