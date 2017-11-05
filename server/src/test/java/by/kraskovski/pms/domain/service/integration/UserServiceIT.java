package by.kraskovski.pms.domain.service.integration;

import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.domain.model.enums.AuthorityEnum;
import by.kraskovski.pms.domain.service.AuthorityService;
import by.kraskovski.pms.domain.service.UserService;
import by.kraskovski.pms.domain.service.integration.config.ServiceTestConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static by.kraskovski.pms.utils.TestUtils.prepareUser;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserServiceIT extends ServiceTestConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Before
    public void setUp() {
        userService.deleteAll();
        authorityService.create(new Authority(AuthorityEnum.ROLE_USER));
    }

    @After
    public void cleanUp() {
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
    public void updateValidTest() {
        final User user = userService.create(prepareUser());
        user.setUsername("UPDATED USERNAME");
        userService.update(user);

        final User updatedUser = userService.findByUsername(user.getUsername());
        assertEquals(user.getUsername(), updatedUser.getUsername());
    }

    @Test
    public void updatePasswordTest() {
        final User user = userService.create(prepareUser());
        final String newPassword = "UPDATED PASSWORD";
        user.setPassword(newPassword);
        userService.update(user);

        final String updatedPassword = userService.find(user.getId()).getPassword();
        assertTrue(encoder.matches(newPassword, updatedPassword));
    }
}
