package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.model.Cart;
import by.kraskovski.pms.repository.CartRepository;
import by.kraskovski.pms.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

    private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(final CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart create(final Cart object) {
        return cartRepository.save(object);
    }

    @Override
    public boolean addProduct(int cartId, int productId, int count) {
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
