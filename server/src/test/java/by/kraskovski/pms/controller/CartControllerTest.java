package by.kraskovski.pms.controller;

import by.kraskovski.pms.controller.config.ControllerConfig;
import by.kraskovski.pms.domain.model.Product;
import by.kraskovski.pms.domain.model.ProductStock;
import by.kraskovski.pms.domain.model.Stock;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.service.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static by.kraskovski.pms.domain.enums.AuthorityEnum.ROLE_ADMIN;
import static by.kraskovski.pms.utils.TestUtils.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CartControllerTest extends ControllerConfig {

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

    @Before
    public void before() {
        cartService.deleteAll();
        userService.deleteAll();
        productService.deleteAll();
        stockService.deleteAll();
        authenticateUserWithAuthority(ROLE_ADMIN);
    }

    @After
    public void after() {
        cleanup();
        cartService.deleteAll();
        userService.deleteAll();
        productService.deleteAll();
        stockService.deleteAll();
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
        final User user = prepareUser();
        userService.create(user);

        mvc.perform(post(BASE_CART_URL + "/" + user.getId())
                .header(authHeaderName, token))
                .andExpect(status().isCreated());
    }

    @Test
    public void addProductToCart() throws Exception {
        final User user = prepareUser();
        userService.create(user);
        cartService.create(user.getId());

        final Product product = prepareProduct();
        productService.create(product);

        final Stock stock = prepareStock();
        stockService.create(stock);
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
    public void deleteProductFromCart() throws Exception {
        final User user = prepareUser();
        userService.create(user);
        cartService.create(user.getId());

        final Product product = prepareProduct();
        productService.create(product);

        final Stock stock = prepareStock();
        stockService.create(stock);
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
    public void deleteCart() throws Exception {
        final User user = prepareUser();
        userService.create(user);
        cartService.create(user.getId());

        mvc.perform(delete(BASE_CART_URL + "/" + user.getId())
                .header(authHeaderName, token))
                .andExpect(status().isNoContent());
    }

}