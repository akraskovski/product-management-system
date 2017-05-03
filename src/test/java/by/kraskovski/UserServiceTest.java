package by.kraskovski;

import by.kraskovski.model.User;
import by.kraskovski.service.AuthorityService;
import by.kraskovski.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    private static User user;

    @Test
    public void testForNullService() {
        assertNotNull(userService);
    }

    @Test
    public void testCreate() {
        user = new User();
        user.setPassword("Test_User");
        user.setPassword("passWorD123.92222");
        userService.create(user);
        userService.delete(user.id);
    }

    @Test
    public void testCreateWithAuthorities() {
        user = new User();
        user.setPassword("Test_User");
        user.setPassword("passWorD123.92222");
        user.setAuthorities(authorityService.findAll());
        userService.create(user);
        userService.delete(user.id);
    }

    @Test
    public void testFindById() {
        int idToFind = 1;
        assertNotNull(userService.find(idToFind));
    }

    @Test
    public void testFindByUsername() {
        String usernameToFind = "admin";
        assertNotNull((userService.findByUsername(usernameToFind)));
    }
}
