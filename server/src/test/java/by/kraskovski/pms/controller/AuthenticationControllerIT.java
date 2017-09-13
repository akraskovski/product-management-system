package by.kraskovski.pms.controller;

import by.kraskovski.pms.controller.config.ControllerConfig;
import by.kraskovski.pms.controller.dto.LoginDto;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.utils.TestUtils;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.apache.commons.lang3.RandomStringUtils.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerIT extends ControllerConfig {

    private static final String BASE_AUTH_URL = "/auth";

    @Test
    public void loginPositiveTest() throws Exception {
        final User user = TestUtils.prepareUser();
        userService.create(user);

        mvc.perform(post(BASE_AUTH_URL + "/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(new LoginDto(user.getUsername(), user.getPassword()))))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.token", notNullValue(String.class)))
                .andExpect(jsonPath("$.user.username", is(user.getUsername())))
                .andExpect(jsonPath("$.user.password", isEmptyString()));
    }

    @Test
    public void loginNegativeTest() throws Exception {
        final User user = TestUtils.prepareUser();
        userService.create(user);

        final LoginDto loginDto = LoginDto.builder()
                .username(randomAlphabetic(20))
                .password(user.getPassword())
                .build();

        mvc.perform(post(BASE_AUTH_URL + "/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isUnauthorized());
    }
}
