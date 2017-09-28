package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.application.controller.config.ControllerConfig;
import by.kraskovski.pms.application.controller.dto.LoginDto;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.utils.TestUtils;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.notNullValue;
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
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.token", notNullValue(String.class)))
                .andExpect(jsonPath("$.userDto.username", is(user.getUsername())))
                .andExpect(jsonPath("$.userDto.password", isEmptyString()));
    }

    @Test
    public void loginNegativeTest() throws Exception {
        mvc.perform(post(BASE_AUTH_URL + "/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(new LoginDto(randomAlphabetic(10), randomAlphabetic(10)))))
                .andExpect(status().isNotFound());
    }
}
