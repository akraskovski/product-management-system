package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.application.controller.config.ControllerTestConfig;
import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.model.enums.AuthorityEnum;
import by.kraskovski.pms.domain.service.AuthorityService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static by.kraskovski.pms.domain.model.enums.AuthorityEnum.ROLE_ADMIN;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthorityControllerIT extends ControllerTestConfig {

    private static final String BASE_AUTHORITY_URL = "/authority";

    @Autowired
    private AuthorityService authorityService;

    @Before
    public void before() {
        authorityService.deleteAll();
        authenticateUserWithAuthority(ROLE_ADMIN);
    }

    @After
    public void after() {
        cleanup();
        authorityService.deleteAll();
    }

    @Test
    public void loadAuthoritiesIfPresentTest() throws Exception {
        final Authority authority = authorityService.create(new Authority(AuthorityEnum.ROLE_USER));
        mvc.perform(get(BASE_AUTHORITY_URL)
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[1].id", is(authority.getId())))
                .andExpect(jsonPath("$[1].name", is(authority.getName().name())));
    }
}