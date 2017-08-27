package by.kraskovski.pms.service.integration;

import by.kraskovski.pms.Application;
import by.kraskovski.pms.domain.User;
import by.kraskovski.pms.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static by.kraskovski.pms.utils.TestUtils.prepareUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceIT {

    @Autowired
    private UserServiceImpl userService;

    @Before
    public void before() {
        userService.deleteAll();
    }

    @After
    public void after() {
        userService.deleteAll();
    }

    @Test
    public void createTest() {
        final User user = prepareUser();
        userService.create(user);

        assertEquals(1, userService.findAll().size());
    }

    @Test
    public void findByIdTest() {
        final User user = prepareUser();
        userService.create(user);

        assertNotNull(userService.find(user.getId()));
    }

    @Test
    public void findByUsernameTest() {
        final User user = prepareUser();
        userService.create(user);

        assertNotNull(userService.findByUsername(user.getUsername()));
    }

    @Test
    public void updateTest() {
        final User user = prepareUser();
        userService.create(user);

        user.setUsername("UPDATED USERNAME");
        userService.update(user);

        final User updatedUser = userService.findByUsername(user.getUsername());
        assertEquals(user.getUsername(), updatedUser.getUsername());
    }
}
