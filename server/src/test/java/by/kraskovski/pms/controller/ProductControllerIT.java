package by.kraskovski.pms.controller;

import by.kraskovski.pms.domain.Product;
import by.kraskovski.pms.service.ProductService;
import org.apache.commons.lang3.RandomUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static by.kraskovski.pms.domain.enums.AuthorityEnum.ROLE_ADMIN;
import static by.kraskovski.pms.utils.TestUtils.prepareProduct;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration test for {@link ProductController}
 */
public class ProductControllerIT extends ControllerConfig {

    private static final String BASE_PRODUCTS_URL = "/product";

    @Autowired
    private ProductService productService;

    @Before
    public void before() {
        productService.deleteAll();
        authenticateUserWithAuthority(ROLE_ADMIN);
    }

    @After
    public void after() {
        cleanup();
        productService.deleteAll();
    }

    @Test
    public void createProductTest() throws Exception {
        final Product product = prepareProduct();

        mvc.perform(post(BASE_PRODUCTS_URL)
                .header(authHeaderName, token)
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.type", is(product.getType())))
                .andExpect(jsonPath("$.image", is(product.getImage())));
    }

    @Test
    public void findProductByIdTest() throws Exception {
        final Product product = prepareProduct();
        productService.create(product);

        mvc.perform(get(BASE_PRODUCTS_URL + "/" + product.getId())
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(product.getId())))
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.type", is(product.getType())))
                .andExpect(jsonPath("$.image", is(product.getImage())));
    }

    @Test
    public void findProductByNameTest() throws Exception {
        final Product product = prepareProduct();
        productService.create(product);

        mvc.perform(get(BASE_PRODUCTS_URL + "/name/" + product.getName())
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(product.getId())))
                .andExpect(jsonPath("$[0].name", is(product.getName())))
                .andExpect(jsonPath("$[0].type", is(product.getType())))
                .andExpect(jsonPath("$[0].image", is(product.getImage())));
    }

    @Test
    public void findProductByTypeTest() throws Exception {
        final Product product = prepareProduct();
        productService.create(product);

        mvc.perform(get(BASE_PRODUCTS_URL + "/type/" + product.getType())
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(product.getId())))
                .andExpect(jsonPath("$[0].name", is(product.getName())))
                .andExpect(jsonPath("$[0].type", is(product.getType())))
                .andExpect(jsonPath("$[0].image", is(product.getImage())));
    }

    @Test
    public void updateProductTest() throws Exception {
        final Product product = prepareProduct();
        productService.create(product);

        product.setName(random(20));
        product.setImage(random(20));
        product.setType(random(20));
        product.setCost(RandomUtils.nextDouble());
        product.setDetails(random(50));
        product.setManufactureDate(null);

        mvc.perform(put(BASE_PRODUCTS_URL)
                .header(authHeaderName, token)
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(product.getId())))
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.type", is(product.getType())))
                .andExpect(jsonPath("$.image", is(product.getImage())));
    }

    @Test
    public void deleteProductTest() throws Exception {
        final Product product = prepareProduct();
        productService.create(product);

        mvc.perform(delete(BASE_PRODUCTS_URL + "/" + product.getId())
                .header(authHeaderName, token))
                .andExpect(status().isNoContent());
    }
}
