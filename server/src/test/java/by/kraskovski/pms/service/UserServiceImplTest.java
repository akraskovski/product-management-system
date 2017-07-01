package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.User;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserService userService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findUserTest() {
        final int id = 10;
        when(userService.find(id)).thenReturn(new User(id, "Test", "Test"));
        final User foundUser = userService.find(id);
        Assert.assertNotNull(foundUser);
    }

    @Test
    public void updateUserTest() {
        final User user = createUser();
        when(userService.create(user)).thenReturn(user);
        when(userService.update(user)).thenReturn(user);
        userService.create(user);
        user.setUsername("Updated user");
        user.setPassword("Updated password");
        userService.update(user);
        verify(userService, times(1)).create(user);
        verify(userService, times(1)).update(user);
    }

    private User createUser() {
        final int id = RandomUtils.nextInt();
        final String username = Arrays.toString(RandomUtils.nextBytes(20));
        final String password = Arrays.toString(RandomUtils.nextBytes(20));
        return new User(id, username, password);
    }
}