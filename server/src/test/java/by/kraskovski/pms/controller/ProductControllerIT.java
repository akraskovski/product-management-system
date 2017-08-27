package by.kraskovski.pms.controller;

import by.kraskovski.pms.domain.Product;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static by.kraskovski.pms.utils.TestUtils.prepareProduct;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerIT extends ITConfiguration{

    @Before
    public void setup() {
        super.setup();
    }

    @Test
    public void createProductTest() throws Exception {
        final Product product = prepareProduct();
        product.setId(StringUtils.EMPTY);

        mvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }
}
