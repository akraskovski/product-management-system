package by.kraskovski.pms.controller;

import by.kraskovski.pms.domain.Product;
import org.junit.Before;
import org.junit.Test;

import static by.kraskovski.pms.domain.enums.AuthorityEnum.ROLE_ADMIN;
import static by.kraskovski.pms.utils.TestUtils.prepareProduct;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;

public class ProductControllerIT extends ControllerConfig {

    private static final String BASE_PRODUCTS_URL = "/product";

    @Before
    public void before() {
        setup(ROLE_ADMIN);
    }

    //    @After
    public void after() {
        cleanup();
    }

    @Test
    public void createProductTest() throws Exception {
        final Product product = prepareProduct();

        mvc.perform(post(BASE_PRODUCTS_URL)
                .contentType(APPLICATION_JSON_UTF8)
                .header(authHeaderName, token)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is(product.getName())));
    }
}
