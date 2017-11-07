package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.application.controller.config.ControllerTestConfig;
import by.kraskovski.pms.application.controller.dto.UserDto;
import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.domain.service.AuthorityService;
import by.kraskovski.pms.domain.service.UserService;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static by.kraskovski.pms.domain.model.enums.AuthorityEnum.ROLE_ADMIN;
import static by.kraskovski.pms.domain.model.enums.AuthorityEnum.ROLE_USER;
import static by.kraskovski.pms.utils.TestUtils.prepareUser;
import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerIT extends ControllerTestConfig {

    private static final String BASE_USER_URL = "/user";

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private Mapper mapper;

    @Before
    public void before() {
        userService.deleteAll();
        authorityService.create(new Authority(ROLE_USER));
        authenticateUserWithAuthority(singletonList(ROLE_ADMIN));
    }

    @After
    public void after() {
        cleanup();
        userService.deleteAll();
        authorityService.deleteAll();
    }

    @Test
    public void loadAllUsersTest() throws Exception {
        final User user = userService.create(prepareUser());

        mvc.perform(get(BASE_USER_URL)
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[1].id", is(user.getId())));
    }

    @Test
    public void loadUserByIdTest() throws Exception {
        final User user = userService.create(prepareUser());

        mvc.perform(get(BASE_USER_URL + "/" + user.getId())
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(user.getId())));
    }

    @Test
    public void loadUserByUsernameTest() throws Exception {
        final User user = userService.create(prepareUser());

        mvc.perform(get(BASE_USER_URL + "/username/" + user.getUsername())
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(user.getId())))
                .andExpect(jsonPath("$.username", is(user.getUsername())));
    }

    @Test
    public void createUserTest() throws Exception {
        final UserDto userDto = mapper.map(prepareUser(), UserDto.class);

        mvc.perform(post(BASE_USER_URL)
                .header(authHeaderName, token)
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.username", is(userDto.getUsername())))
                .andExpect(jsonPath("$.password", notNullValue(String.class)))
                .andExpect(jsonPath("$.firstName", is(userDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(userDto.getLastName())))
                .andExpect(jsonPath("$.email", is(userDto.getEmail())))
                .andExpect(jsonPath("$.phone", is(userDto.getPhone())))
                .andExpect(jsonPath("$.createDate", notNullValue(LocalDateTime.class)));
    }

    @Test
    public void updateUserTest() throws Exception {
        final User user = userService.create(prepareUser());
        user.setUsername(randomAlphabetic(20));
        user.setPassword(randomAlphabetic(5) + randomNumeric(5));
        user.setFirstName(randomAlphabetic(20));
        user.setLastName(randomAlphabetic(20));
        user.setPhone(randomNumeric(20));

        mvc.perform(put(BASE_USER_URL)
                .header(authHeaderName, token)
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(mapper.map(user, UserDto.class))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(user.getId())))
                .andExpect(jsonPath("$.username", is(user.getUsername())))
                .andExpect(jsonPath("$.password", notNullValue()))
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.phone", is(user.getPhone())));
    }

    @Test
    public void deleteUserTest() throws Exception {
        final User user = userService.create(prepareUser());

        mvc.perform(delete(BASE_USER_URL + "/" + user.getId())
                .header(authHeaderName, token))
                .andExpect(status().isNoContent());
    }
}