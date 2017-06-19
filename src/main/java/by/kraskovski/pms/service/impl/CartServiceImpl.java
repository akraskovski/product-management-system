package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.model.Cart;
import by.kraskovski.pms.model.CartProductStock;
import by.kraskovski.pms.model.ProductStock;
import by.kraskovski.pms.repository.CartRepository;
import by.kraskovski.pms.service.CartService;
import by.kraskovski.pms.service.ProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private ProductStockService productStockService;

    @Autowired
    public CartServiceImpl(final CartRepository cartRepository, final ProductStockService productStockService) {
        this.cartRepository = cartRepository;
        this.productStockService = productStockService;
    }

    @Override
    public Cart create(final Cart object) {
        return cartRepository.save(object);
    }

    @Override
    public boolean addProduct(final int cartId, final int productStockId, final int count) {
        final Cart cart = find(cartId);
        final ProductStock productStock = productStockService.find(productStockId);

        if (cart == null && productStock == null) {
            return false;
        }

        for (CartProductStock cartProductStock : cart.getCartProductStocks()) {
            if (cartProductStock.getProductStock().equals(productStock)) {
                return addExistingProductToCart(cartProductStock, cart, count);
            }
        }
        return addNewProductToCart(cart, productStock, count);
    }

    private boolean addExistingProductToCart(final CartProductStock cartProductStock, final Cart cart, final int count) {
        if (cartProductStock.getProductStock().productsCount - (cartProductStock.getProductCount() + count) > 0) {
            cartProductStock.setProductCount(cartProductStock.getProductCount() + count);
            cart.setTotalCost(cart.getTotalCost() + cartProductStock.getProductStock().getProduct().getCost() * count);
            cartRepository.save(cart);
            return true;
        }
        return false;
    }

    private boolean addNewProductToCart(final Cart cart, final ProductStock productStock, final int count) {
        if (productStock.productsCount - count > 0) {
            final CartProductStock cartProductStock = new CartProductStock();
            cartProductStock.setCart(cart);
            cartProductStock.setProductStock(productStock);
            cartProductStock.setProductCount(count);
            cart.getCartProductStocks().add(cartProductStock);
            cart.setTotalCost(cart.getTotalCost() + productStock.getProduct().getCost() * count);
            cartRepository.save(cart);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int cartId, int productId, int count) {
        return false;
    }

    @Override
    public Cart find(final int id) {
        return cartRepository.findOne(id);
    }

    @Override
    public Cart update(final Cart object) {
        return cartRepository.save(object);
    }

    @Override
    public void delete(final int id) {
        cartRepository.delete(id);
    }
}
