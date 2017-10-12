package by.kraskovski.pms.application.security.service;

import by.kraskovski.pms.application.security.exception.UserNotFoundException;
import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.domain.model.enums.AuthorityEnum;
import by.kraskovski.pms.domain.service.AuthorityService;
import by.kraskovski.pms.domain.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

import static by.kraskovski.pms.utils.TestUtils.prepareUserWithRole;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

    @Value("${auth.header.name:x-auth-token}")
    private String authHeaderName;

    @Before
    public void before() {
        userService.deleteAll();
        authorityService.deleteAll();
    }

    @Test
    public void generateWithValidDataTest() {
        final User user = createUser();
        final String token = tokenService.generate(user.getUsername(), user.getPassword()).getToken();
        validateTokenData(token, user);
    }

    @Test(expected = UserNotFoundException.class)
    public void generateIfUserNotExistsTest() {
        tokenService.generate(randomAlphabetic(5), randomAlphabetic(5));
    }

    @Test(expected = BadCredentialsException.class)
    public void generateIfEmptyPasswordTest() {
        final User user = createUser();
        tokenService.generate(user.getUsername(), "");
    }

    @Test(expected = BadCredentialsException.class)
    public void generateIfPasswordNotEqualsTest() {
        final User user = createUser();
        tokenService.generate(user.getUsername(), randomAlphabetic(50));
    }

    @Test
    public void authenticateWithValidDataTest() {
        final User user = createUser();
        final String token = tokenService.generate(user.getUsername(), user.getPassword()).getToken();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(authHeaderName, token);

        final Authentication authentication = tokenService.authenticate(request);

        assertTrue(authentication.isAuthenticated());
        assertNotNull(authentication);
    }

    @Test
    public void authenticateWithInvalidHeadersTest() {
        final User user = createUser();
        final String token = tokenService.generate(user.getUsername(), user.getPassword()).getToken();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(randomAlphabetic(20), token);

        final Authentication authentication = tokenService.authenticate(request);

        assertNull(authentication);
    }

    @Test
    public void authenticateWithEmptyTokenTest() {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(authHeaderName, "");

        final Authentication authentication = tokenService.authenticate(request);

        assertNull(authentication);
    }

    @Test
    public void authenticateIfUserChangedPasswordTest() {
        final User user = createUser();
        final String token = tokenService.generate(user.getUsername(), user.getPassword()).getToken();

        user.setPassword(randomAlphabetic(20));
        userService.update(user);

        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(authHeaderName, token);

        final Authentication authentication = tokenService.authenticate(request);

        assertNull(authentication);
    }

    @Test(expected = ExpiredJwtException.class)
    public void authenticateIfTokenExpirationFinishedTest() {
        final User user = createUser();
        String token = tokenService.generate(user.getUsername(), user.getPassword()).getToken();
        token = modifyTokenExpirationTime(token);

        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(authHeaderName, token);

        final Authentication authentication = tokenService.authenticate(request);

        assertNull(authentication);
    }

    private String modifyTokenExpirationTime(final String token) {
        final Jws<Claims> tokenData = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        final JwtBuilder jwtBuilder = Jwts.builder();
        final Calendar calendar = Calendar.getInstance();

        jwtBuilder.setClaims(tokenData.getBody());
        calendar.add(Calendar.MILLISECOND, 1);
        jwtBuilder.setExpiration(calendar.getTime());
        return jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey).compact();
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
