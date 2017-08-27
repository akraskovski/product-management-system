package by.kraskovski.pms.controller;

import by.kraskovski.pms.domain.Product;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static by.kraskovski.pms.domain.enums.AuthorityEnum.ROLE_ADMIN;
import static by.kraskovski.pms.utils.TestUtils.prepareProduct;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerIT extends ITConfiguration {

    private static final String BASE_PRODUCTS_URL = "/product";

    @Before
    public void before() {
        setup(ROLE_ADMIN);
    }

    @Test
    public void createProductTest() throws Exception {
        final Product product = prepareProduct();
        product.setId(StringUtils.EMPTY);

        mvc.perform(post(BASE_PRODUCTS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .header(authHeaderName, token)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }
}
