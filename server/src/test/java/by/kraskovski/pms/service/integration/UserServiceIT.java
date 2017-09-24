package by.kraskovski.pms.service.integration;

import by.kraskovski.pms.domain.enums.AuthorityEnum;
import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.service.AuthorityService;
import by.kraskovski.pms.service.UserService;
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
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceIT {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @Before
    public void before() {
        userService.deleteAll();
        authorityService.create(new Authority(AuthorityEnum.ROLE_USER));
    }

    @After
    public void after() {
        userService.deleteAll();
        authorityService.deleteAll();
    }

    @Test
    public void createTest() {
        userService.create(prepareUser());
        assertEquals(1, userService.findAll().size());
    }

    @Test
    public void findByIdTest() {
        final User user = userService.create(prepareUser());
        assertNotNull(userService.find(user.getId()));
    }

    @Test
    public void findByUsernameTest() {
        final User user = userService.create(prepareUser());
        assertNotNull(userService.findByUsername(user.getUsername()));
    }

    @Test
    public void updateTest() {
        final User user = userService.create(prepareUser());
        user.setUsername("UPDATED USERNAME");
        userService.update(user);

        final User updatedUser = userService.findByUsername(user.getUsername());
        assertEquals(user.getUsername(), updatedUser.getUsername());
    }
}
