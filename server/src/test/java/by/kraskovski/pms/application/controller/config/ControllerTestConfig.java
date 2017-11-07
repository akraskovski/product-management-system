package by.kraskovski.pms.application.controller.config;

import by.kraskovski.pms.application.controller.dto.TokenDto;
import by.kraskovski.pms.application.security.model.JwtAuthenticationFactory;
import by.kraskovski.pms.domain.model.enums.AuthorityEnum;
import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.application.security.service.TokenService;
import by.kraskovski.pms.domain.service.AuthorityService;
import by.kraskovski.pms.domain.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

import static by.kraskovski.pms.utils.TestUtils.prepareUserWithRole;

/**
 * Abstract configuration class for all controllers integration tests
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class ControllerTestConfig {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    protected UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Autowired
    protected ObjectMapper objectMapper;

    @Value("${auth.header.name:x-auth-token}")
    protected String authHeaderName;

    private User userDto;

    protected String token;

    protected void authenticateUserWithAuthority(final List<AuthorityEnum> authoritiesEnum) {
        final List<Authority> authorities = authoritiesEnum.stream()
                .map(authorityEnum -> authorityService.create(new Authority(authorityEnum)))
                .collect(Collectors.toList());
        userDto = prepareUserWithRole(authorities);
        userService.create(userDto);
        final TokenDto tokenDto = tokenService.generate(userDto.getUsername(), userDto.getPassword());
        token = tokenDto.getToken();
        SecurityContextHolder.getContext().setAuthentication(JwtAuthenticationFactory.create(dozerBeanMapper.map(tokenDto.getUserDto(), User.class)));
    }

    protected void cleanup() {
        userService.delete(userDto.getId());
        userDto.getAuthorities().forEach(authority -> authorityService.delete(authority.getId()));
    }
}
