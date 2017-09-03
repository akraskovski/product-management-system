package by.kraskovski.pms.controller.config;

import by.kraskovski.pms.domain.Authority;
import by.kraskovski.pms.domain.User;
import by.kraskovski.pms.domain.enums.AuthorityEnum;
import by.kraskovski.pms.security.service.TokenService;
import by.kraskovski.pms.service.AuthorityService;
import by.kraskovski.pms.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static by.kraskovski.pms.utils.TestUtils.prepareUserWithRole;

/**
 * Abstract configuration class for all controllers integration tests
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class ControllerConfig {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    protected UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    protected ObjectMapper objectMapper;

    @Value("${auth.header.name:x-auth-token}")
    protected String authHeaderName;

    private User user;

    protected String token;

    protected void authenticateUserWithAuthority(final AuthorityEnum authorityName) {
        final Authority authority = authorityService.create(new Authority(authorityName));
        user = prepareUserWithRole(authority);
        userService.create(user);
        token = tokenService.generate(user.getUsername(), user.getPassword()).getToken();
    }

    protected void cleanup() {
        userService.delete(user.getId());
        authorityService.delete(user.getAuthorities().get(0).getId());
    }
}
