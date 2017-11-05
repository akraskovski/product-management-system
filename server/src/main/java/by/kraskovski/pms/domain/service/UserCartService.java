package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.Cart;
import by.kraskovski.pms.domain.model.CartProductStock;
import by.kraskovski.pms.domain.model.ProductStock;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.domain.repository.CartRepository;
import by.kraskovski.pms.domain.service.stock.ProductStockService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserCartService implements CartService {

    private final CartRepository cartRepository;
    private final ProductStockService productStockService;
    private final UserService userService;

    @Override
    public void create(final String id) {
        final User user = userService.find(id);
        if (Objects.nonNull(user.getCart())) {
            throw new IllegalArgumentException("Cart with id:" + id + " already exists!");
        }
        user.createCart();
        userService.update(user);
    }

    @Override
    @Transactional
    public void addProduct(final String cartId, final String productStockId, final int count) {
        final Cart cart = find(cartId);
        final ProductStock productStock = productStockService.find(productStockId);

        //TODO: change to stream
        for (CartProductStock cartProductStock : cart.getCartProductStocks()) {
            if (cartProductStock.getProductStock().equals(productStock)) {
                addExistingProductToCart(cartProductStock, cart, count);
                return;
            }
        }
        addNewProductToCart(cart, productStock, count);
    }

    private void addExistingProductToCart(final CartProductStock cartProductStock, final Cart cart, final int count) {
        if (cartProductStock.getProductStock().getProductsCount() - (cartProductStock.getProductCount() + count) < 0) {
            throw new IllegalArgumentException("The number of products in stock less than in request!");
        }
        cartProductStock.setProductCount(cartProductStock.getProductCount() + count);
        cart.setTotalCost(cart.getTotalCost() + cartProductStock.getProductStock().getProduct().getCost() * count);
        cartRepository.save(cart);
    }

    private void addNewProductToCart(final Cart cart, final ProductStock productStock, final int count) {
        if (productStock.getProductsCount() - count < 0) {
            throw new IllegalArgumentException("The number of products in stock less than in request!");
        }
        final CartProductStock cartProductStock = new CartProductStock(cart, productStock, count);
        cart.getCartProductStocks().add(cartProductStock);
        cart.setTotalCost(cart.getTotalCost() + productStock.getProduct().getCost() * count);
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void deleteProduct(final String cartId, final String productStockId, final int count) {
        final Cart cart = find(cartId);
        final ProductStock productStock = productStockService.find(productStockId);

        cart.getCartProductStocks().stream()
                .filter(cartProductStock -> cartProductStock.getProductStock().equals(productStock))
                .findFirst()
                .ifPresent(cartProductStock -> deleteProductFromCartProductStock(cart, cartProductStock, count));
    }

    private void deleteProductFromCartProductStock(
            final Cart cart,
            final CartProductStock cartProductStock,
            final int count) {
        if (cartProductStock.getProductCount() - count > 0) {
            cartProductStock.setProductCount(cartProductStock.getProductCount() - count);
            cart.setTotalCost(cart.getTotalCost() - cartProductStock.getProductStock().getProduct().getCost() * count);
            cartRepository.save(cart);
        } else if (cartProductStock.getProductCount() - count == 0) {
            cart.getCartProductStocks().remove(cartProductStock);
            cart.setTotalCost(cart.getTotalCost() - cartProductStock.getProductStock().getProduct().getCost() * count);
            cartRepository.save(cart);
        } else {
            throw new IllegalArgumentException("The number of products in stock less than in request!");
        }
    }

    @Override
    public Cart find(final String id) {
        return Optional.ofNullable(cartRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Cart with id: " + id + " not found in db!"));
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart update(final Cart object) {
        return cartRepository.save(object);
    }

    @Override
    public void delete(final String id) {
        final User user = userService.find(id);
        if (user.getCart() != null) {
            user.removeCart();
            userService.update(user);
        }
    }

    @Override
    public void deleteAll() {
        cartRepository.deleteAll();
    }
}
