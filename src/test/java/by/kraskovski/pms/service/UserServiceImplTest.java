package by.kraskovski.pms.service;

import by.kraskovski.pms.model.User;
import by.kraskovski.pms.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private UserService userService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void find() {
        final int id = 10;
        when(userService.find(id)).thenReturn(new User(id, "Test", "Test"));
        User foundUser = userService.find(id);
        Assert.assertNotNull(foundUser);
    }
}