package by.kraskovski.pms.application.security.service;

import by.kraskovski.pms.application.security.exception.UserNotFoundException;
import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.domain.model.enums.AuthorityEnum;
import by.kraskovski.pms.domain.service.AuthorityService;
import by.kraskovski.pms.domain.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static by.kraskovski.pms.utils.TestUtils.prepareUserWithRole;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;

/**
 * Integration test for main security logic of application.
 * Check generating and verifying JWT Tokens.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TokenServiceTest {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserService userService;

    @Value("${secret.key:JKGuhygvuh2v}")
    private String secretKey;

    @Before
    public void before() {
        userService.deleteAll();
        authorityService.deleteAll();
    }

    @Test
    public void generateWithValidDataTest() throws Exception {
        final User user = createUser();
        final String token = tokenService.generate(user.getUsername(), user.getPassword()).getToken();
        validateTokenData(token, user);
    }

    @Test(expected = UserNotFoundException.class)
    public void generateIfUserNotExistsTest() throws Exception {
        tokenService.generate(randomAlphabetic(5), randomAlphabetic(5));
    }

    @Test(expected = BadCredentialsException.class)
    public void generateIfEmptyPasswordTest() throws Exception {
        final User user = createUser();
        tokenService.generate(user.getUsername(), "");
    }

    @Test(expected = BadCredentialsException.class)
    public void generateIfPasswordNotEqualsTest() throws Exception {
        final User user = createUser();
        tokenService.generate(user.getUsername(), randomAlphabetic(50));
    }

    private User createUser() {
        final Authority authority = new Authority(AuthorityEnum.ROLE_ADMIN);
        authorityService.create(authority);
        final User user = prepareUserWithRole(authority);
        return userService.create(user);
    }

    private void validateTokenData(final String token, final User user) {
        final Jws<Claims> tokenData = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        final String username = tokenData.getBody().get("username").toString();
        final String password = tokenData.getBody().get("password").toString();
        final User userFromToken = userService.findByUsername(username);

        assertEquals(user.getUsername(), userFromToken.getUsername());
        assertEquals(user.getPassword(), password);
    }
}
