package by.intexsoft;

import by.intexsoft.model.User;
import by.intexsoft.service.AuthorityService;
import by.intexsoft.service.UserService;
import org.junit.After;
import org.junit.Before;
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

    @Before
    public void before() {
        userService.deleteAll();
    }

    @After
    public void after() {
        userService.deleteAll();
    }

    @Test
    public void testForNullService() {
        assertNotNull(userService);
    }

    @Test
    public void testCreate() {
        user = new User();
        user.username = "Test_User";
        user.password = "293922qweqwe";
        userService.create(user);
        userService.delete(user.id);
    }

    @Test
    public void testCreateWithAuthorities() {
        user = new User();
        user.username = "Test_User1";
        user.password = "293922qweqwe";
        user.authorities = authorityService.findAll();
        userService.create(user);
    }

    @Test
    public void testFindById() {
        Integer idToFind = 1;
        assertNotNull(userService.find(idToFind));
    }

    @Test
    public void testFindByUsername() {
        String usernameToFind = "admin";
        assertNotNull((userService.findByUsername(usernameToFind)));
    }

    @Test
    public void testUpdate() {
        user = new User();
        user.username = "Test_User2";
        user.password = "123903rt4uierhjg";
        user = userService.create(user);
        user.username = "Test_User_Edited";
        user = userService.update(user);
        userService.delete(user.id);
    }
}
