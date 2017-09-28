package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.application.controller.config.ControllerConfig;
import by.kraskovski.pms.domain.model.*;
import by.kraskovski.pms.domain.service.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static by.kraskovski.pms.domain.model.enums.AuthorityEnum.ROLE_ADMIN;
import static by.kraskovski.pms.domain.model.enums.AuthorityEnum.ROLE_USER;
import static by.kraskovski.pms.utils.TestUtils.prepareProduct;
import static by.kraskovski.pms.utils.TestUtils.prepareStock;
import static by.kraskovski.pms.utils.TestUtils.prepareUser;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CartControllerIT extends ControllerConfig {

    private static final String BASE_CART_URL = "/cart";

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductStockService productStockService;

    @Autowired
    private AuthorityService authorityService;

    @Before
    public void before() {
        cartService.deleteAll();
        userService.deleteAll();
        productService.deleteAll();
        stockService.deleteAll();
        authorityService.create(new Authority(ROLE_USER));
        authenticateUserWithAuthority(ROLE_ADMIN);
    }

    @After
    public void after() {
        cleanup();
        cartService.deleteAll();
        userService.deleteAll();
        productService.deleteAll();
        stockService.deleteAll();
        authorityService.deleteAll();
    }

    @Test
    public void loadCartByIdIfExistsTest() throws Exception {
        final User user = userService.create(prepareUser());
        cartService.create(user.getId());
        mvc.perform(get(BASE_CART_URL + "/" + user.getId())
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(user.getId())));
    }

    @Test
    public void loadCartByIdIfNotExistsTest() throws Exception {
        mvc.perform(get(BASE_CART_URL + "/" + randomAlphabetic(20))
                .header(authHeaderName, token))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createCartWithExistsUserTest() throws Exception {
        final User user = prepareUser();
        userService.create(user);
        mvc.perform(post(BASE_CART_URL + "/" + user.getId())
                .header(authHeaderName, token))
                .andExpect(status().isCreated());
    }

    @Test
    public void createCartWithoutUserTest() throws Exception {
        mvc.perform(post(BASE_CART_URL + "/" + randomAlphabetic(20))
                .header(authHeaderName, token))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addProductToCartPositiveTest() throws Exception {
        final User user = userService.create(prepareUser());
        final Product product = productService.create(prepareProduct());
        final Stock stock = stockService.create(prepareStock());
        cartService.create(user.getId());
        stockService.addProduct(stock.getId(), product.getId(), 10);
        final ProductStock productStock = productStockService.findByStockIdAndProductId(stock.getId(), product.getId());
        mvc.perform(put(BASE_CART_URL)
                .header(authHeaderName, token)
                .param("cart_id", user.getId())
                .param("ps_id", productStock.getId())
                .param("count", "10"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void addProductToCartNegativeTest() throws Exception {
        final User user = userService.create(prepareUser());
        final Product product = productService.create(prepareProduct());
        final Stock stock = stockService.create(prepareStock());
        cartService.create(user.getId());
        stockService.addProduct(stock.getId(), product.getId(), 10);
        final ProductStock productStock = productStockService.findByStockIdAndProductId(stock.getId(), product.getId());
        mvc.perform(put(BASE_CART_URL)
                .header(authHeaderName, token)
                .param("cart_id", user.getId())
                .param("ps_id", productStock.getId())
                .param("count", "15"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteProductFromCartPositiveTest() throws Exception {
        final User user = userService.create(prepareUser());
        final Product product = productService.create(prepareProduct());
        final Stock stock = stockService.create(prepareStock());
        cartService.create(user.getId());
        stockService.addProduct(stock.getId(), product.getId(), 10);
        final ProductStock productStock = productStockService.findByStockIdAndProductId(stock.getId(), product.getId());
        cartService.addProduct(user.getId(), productStock.getId(), 10);
        mvc.perform(delete(BASE_CART_URL)
                .header(authHeaderName, token)
                .param("cart_id", user.getId())
                .param("ps_id", productStock.getId())
                .param("count", "5"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteProductFromCartNegativeTest() throws Exception {
        final User user = userService.create(prepareUser());
        final Product product = productService.create(prepareProduct());
        final Stock stock = stockService.create(prepareStock());
        cartService.create(user.getId());
        stockService.addProduct(stock.getId(), product.getId(), 10);
        final ProductStock productStock = productStockService.findByStockIdAndProductId(stock.getId(), product.getId());
        cartService.addProduct(user.getId(), productStock.getId(), 10);
        mvc.perform(delete(BASE_CART_URL)
                .header(authHeaderName, token)
                .param("cart_id", user.getId())
                .param("ps_id", productStock.getId())
                .param("count", "11"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteCartTest() throws Exception {
        final User user = userService.create(prepareUser());
        cartService.create(user.getId());

        mvc.perform(delete(BASE_CART_URL + "/" + user.getId())
                .header(authHeaderName, token))
                .andExpect(status().isNoContent());
    }
}