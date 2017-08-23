package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.Cart;
import by.kraskovski.pms.domain.ProductStock;
import by.kraskovski.pms.domain.User;
import by.kraskovski.pms.repository.CartRepository;
import by.kraskovski.pms.service.impl.CartServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.management.InstanceAlreadyExistsException;

import static by.kraskovski.pms.utils.TestUtils.*;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductStockService productStockService;

    @Mock
    private UserService userService;

    @InjectMocks
    private CartServiceImpl cartService;

    @Test
    public void createPositiveTest() throws InstanceAlreadyExistsException {
        final User user = prepareUser();
        user.removeCart();
        when(userService.find(anyInt())).thenReturn(user);
        when(userService.update(anyObject())).thenReturn(user);

        cartService.create(nextInt());

        verify(userService).find(anyInt());
        verify(userService).update(anyObject());
    }

    @Test(expected = InstanceAlreadyExistsException.class)
    public void createNegativeTest() throws InstanceAlreadyExistsException {
        final User user = prepareUser();
        when(userService.find(anyInt())).thenReturn(user);
        when(userService.update(anyObject())).thenReturn(user);

        cartService.create(nextInt());

        verify(userService).find(anyInt());
        verify(userService).update(anyObject());
    }

    @Test
    public void addNewProductToCartPositiveTest() {
        when(cartRepository.findOne(anyInt())).thenReturn(new Cart(prepareUser()));
        when(productStockService.find(anyInt())).thenReturn(new ProductStock(prepareProduct(), prepareStock(), 20));

        assertTrue(cartService.addProduct(nextInt(), nextInt(), 10));

        verify(cartRepository).findOne(anyInt());
        verify(productStockService).find(anyInt());
        verify(cartRepository).save((Cart) anyObject());
    }

    @Test
    public void addNewProductToCartNegativeTest() {
        when(cartRepository.findOne(anyInt())).thenReturn(new Cart(prepareUser()));
        when(productStockService.find(anyInt())).thenReturn(new ProductStock(prepareProduct(), prepareStock(), 10));

        assertFalse(cartService.addProduct(nextInt(), nextInt(), 11));

        verify(cartRepository).findOne(anyInt());
        verify(cartRepository, times(0)).save((Cart) anyObject());
        verify(productStockService).find(anyInt());
    }

    @Test
    public void addExistingProductToCartPositiveTest() {
        final Cart cart = new Cart(prepareUser());
        final ProductStock productStock = new ProductStock(prepareProduct(), prepareStock(), 20);

        when(cartRepository.findOne(anyInt())).thenReturn(cart);
        when(productStockService.find(anyInt())).thenReturn(productStock);

        assertTrue(cartService.addProduct(nextInt(), nextInt(), 10));

        verify(cartRepository).save((Cart) anyObject());
    }

    @Test
    public void addExistingProductToCartNegativeTest() {
        final Cart cart = new Cart(prepareUser());
        final ProductStock productStock = new ProductStock(prepareProduct(), prepareStock(), 10);

        when(cartRepository.findOne(anyInt())).thenReturn(cart);
        when(productStockService.find(anyInt())).thenReturn(productStock);

        assertFalse(cartService.addProduct(nextInt(), nextInt(), 11));
        verify(cartRepository, times(0)).save((Cart) anyObject());
    }
}