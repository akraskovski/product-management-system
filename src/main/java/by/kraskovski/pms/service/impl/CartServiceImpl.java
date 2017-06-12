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
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart create(Cart object) {
        return cartRepository.save(object);
    }

    @Override
    public Cart find(int id) {
        return cartRepository.findOne(id);
    }

    @Override
    public Cart update(Cart object) {
        return cartRepository.save(object);
    }

    @Override
    public void delete(int id) {
        cartRepository.delete(id);
    }
}
