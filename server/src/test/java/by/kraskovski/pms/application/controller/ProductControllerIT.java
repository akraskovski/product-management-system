package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.application.controller.config.ControllerTestConfig;
import by.kraskovski.pms.application.controller.dto.ProductDto;
import by.kraskovski.pms.domain.model.Product;
import by.kraskovski.pms.domain.service.ProductService;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static by.kraskovski.pms.domain.model.enums.AuthorityEnum.ROLE_ADMIN;
import static by.kraskovski.pms.utils.TestUtils.prepareProduct;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration test for {@link ProductController}
 */
public class ProductControllerIT extends ControllerTestConfig {

    private static final String BASE_PRODUCTS_URL = "/product";

    @Autowired
    private ProductService productService;

    @Autowired
    private Mapper mapper;

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
    public void createValidProductTest() throws Exception {
        mvc.perform(post(BASE_PRODUCTS_URL)
                .header(authHeaderName, token)
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(mapper.map(prepareProduct(), ProductDto.class))))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    public void createInvalidProductTest() throws Exception {
        final Product product = prepareProduct();
        product.setName(null);
        mvc.perform(post(BASE_PRODUCTS_URL)
                .header(authHeaderName, token)
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(mapper.map(product, ProductDto.class))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findProductByIdIfExistsTest() throws Exception {
        final Product product = productService.create(prepareProduct());
        mvc.perform(get(BASE_PRODUCTS_URL + "/" + product.getId())
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(product.getId())));
    }

    @Test
    public void findProductByIdIfNotExistsTest() throws Exception {
        mvc.perform(get(BASE_PRODUCTS_URL + "/" + randomAlphabetic(10))
                .header(authHeaderName, token))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findProductsByNameIfExistsTest() throws Exception {
        final Product product = productService.create(prepareProduct());
        productService.create(product);
        mvc.perform(get(BASE_PRODUCTS_URL + "/name/" + product.getName())
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(product.getId())));
    }

    @Test
    public void findProductsByNameIfNotExistsTest() throws Exception {
        mvc.perform(get(BASE_PRODUCTS_URL + "/name/" + randomAlphabetic(10))
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string("[]"));
    }

    @Test
    public void findProductsByTypeIfExistsTest() throws Exception {
        final Product product = productService.create(prepareProduct());
        mvc.perform(get(BASE_PRODUCTS_URL + "/type/" + product.getType())
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(product.getId())));
    }

    @Test
    public void findProductsByTypeIfNotExistsTest() throws Exception {
        mvc.perform(get(BASE_PRODUCTS_URL + "/type/" + randomAlphabetic(10))
                .header(authHeaderName, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string("[]"));
    }

    @Test
    public void updateProductWithValidDataTest() throws Exception {
        final Product product = productService.create(prepareProduct());
        product.setName(random(20));
        product.setImage(random(20));
        product.setType(random(20));
        mvc.perform(put(BASE_PRODUCTS_URL)
                .header(authHeaderName, token)
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(mapper.map(product, ProductDto.class))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(product.getId())))
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.type", is(product.getType())))
                .andExpect(jsonPath("$.image", is(product.getImage())));
    }

    @Test
    public void updateProductWithInvalidDataTest() throws Exception {
        final Product product = productService.create(prepareProduct());
        product.setName(null);
        mvc.perform(put(BASE_PRODUCTS_URL)
                .header(authHeaderName, token)
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(mapper.map(product, ProductDto.class))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteProductWithValidDataTest() throws Exception {
        final Product product = prepareProduct();
        product.setImage(null);
        productService.create(product);
        mvc.perform(delete(BASE_PRODUCTS_URL + "/" + product.getId())
                .header(authHeaderName, token))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteProductWithInvalidImageTest() throws Exception {
        final Product product = prepareProduct();
        product.setImage(randomAlphabetic(20));
        productService.create(product);
        mvc.perform(delete(BASE_PRODUCTS_URL + "/" + product.getId())
                .header(authHeaderName, token))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteProductWithInvalidIdTest() throws Exception {
        mvc.perform(delete(BASE_PRODUCTS_URL + "/" + randomAlphabetic(20))
                .header(authHeaderName, token))
                .andExpect(status().isNotFound());
    }
}
