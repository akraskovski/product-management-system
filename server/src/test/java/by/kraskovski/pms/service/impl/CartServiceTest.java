package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.domain.Cart;
import by.kraskovski.pms.domain.User;
import by.kraskovski.pms.repository.CartRepository;
import by.kraskovski.pms.service.ProductStockService;
import by.kraskovski.pms.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.management.InstanceAlreadyExistsException;

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

    private User prepareUser() {
        final int id = RandomUtils.nextInt();
        final String username = RandomStringUtils.random(20);
        final String password = RandomStringUtils.random(20);
        return new User(id, username, password);
    }

    @Test
    public void createPositiveTest() throws InstanceAlreadyExistsException {
        final User user = prepareUser();
        when(userService.find(anyInt())).thenReturn(user);
        when(userService.update(anyObject())).thenReturn(user);

        cartService.create(RandomUtils.nextInt());

        verify(userService).find(anyInt());
        verify(userService).update(anyObject());
    }

    @Test(expected = InstanceAlreadyExistsException.class)
    public void createNegativeTest() throws InstanceAlreadyExistsException {
        final User user = prepareUser();
        user.addCart(new Cart());
        when(userService.find(anyInt())).thenReturn(user);
        when(userService.update(anyObject())).thenReturn(user);

        cartService.create(RandomUtils.nextInt());

        verify(userService).find(anyInt());
        verify(userService).update(anyObject());
    }
}