package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.*;
import by.kraskovski.pms.repository.CartRepository;
import by.kraskovski.pms.service.impl.CartServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.management.InstanceAlreadyExistsException;

import static by.kraskovski.pms.utils.TestUtils.prepareCart;
import static by.kraskovski.pms.utils.TestUtils.prepareProductStock;
import static by.kraskovski.pms.utils.TestUtils.prepareUser;
import static org.apache.commons.lang3.RandomUtils.*;
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
    public void addExistingProductToCartTest() {
        final Cart cart = prepareCart();
        final ProductStock productStock = prepareProductStock();
        cart.getCartProductStocks().add(new CartProductStock(cart, productStock, 10));
        when(cartRepository.findOne(anyInt())).thenReturn(cart);
        when(productStockService.find(anyInt())).thenReturn(productStock);

        cartService.addProduct(nextInt(), nextInt(), 5);


    }
}