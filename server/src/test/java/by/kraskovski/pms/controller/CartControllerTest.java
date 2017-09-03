package by.kraskovski.pms.controller;

import by.kraskovski.pms.controller.config.ControllerConfig;
import by.kraskovski.pms.domain.User;
import by.kraskovski.pms.service.CartService;
import by.kraskovski.pms.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static by.kraskovski.pms.domain.enums.AuthorityEnum.ROLE_ADMIN;
import static by.kraskovski.pms.utils.TestUtils.prepareUser;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CartControllerTest extends ControllerConfig {

    private static final String BASE_CART_URL = "/cart";

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Before
    public void before() {
        cartService.deleteAll();
        authenticateUserWithAuthority(ROLE_ADMIN);
    }

    @After
    public void after() {
        cleanup();
        cartService.deleteAll();
    }

    @Test
    public void loadCartById() throws Exception {
        final User user = prepareUser();
        userService.create(user);
        cartService.create(user.getId());

        mvc.perform(get(BASE_CART_URL + "/" + user.getId())
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(user.getId())));
    }

    @Test
    public void createCart() throws Exception {
    }

    @Test
    public void addProductToCart() throws Exception {
    }

    @Test
    public void deleteProductFromCart() throws Exception {
    }

    @Test
    public void deleteCart() throws Exception {
    }

}