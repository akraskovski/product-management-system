package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.User;
import by.kraskovski.pms.repository.CartRepository;
import by.kraskovski.pms.service.impl.CartServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.management.InstanceAlreadyExistsException;

import static by.kraskovski.pms.utils.TestUtils.prepareUser;
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

        cartService.create(RandomUtils.nextInt());

        verify(userService).find(anyInt());
        verify(userService).update(anyObject());
    }

    @Test(expected = InstanceAlreadyExistsException.class)
    public void createNegativeTest() throws InstanceAlreadyExistsException {
        final User user = prepareUser();
        when(userService.find(anyInt())).thenReturn(user);
        when(userService.update(anyObject())).thenReturn(user);

        cartService.create(RandomUtils.nextInt());

        verify(userService).find(anyInt());
        verify(userService).update(anyObject());
    }
}