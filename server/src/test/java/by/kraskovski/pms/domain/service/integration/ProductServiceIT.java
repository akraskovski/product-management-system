package by.kraskovski.pms.domain.service.integration;

import by.kraskovski.pms.domain.model.Product;
import by.kraskovski.pms.domain.service.ProductService;
import by.kraskovski.pms.domain.service.integration.config.ServiceTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static by.kraskovski.pms.utils.TestUtils.prepareProduct;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Integration test for {@link ProductService}.
 */
public class ProductServiceIT extends ServiceTestConfig {

    @Autowired
    private ProductService productService;

    @Before
    public void setUp() {
        productService.deleteAll();
    }

    @Test
    public void createWithValidObjectTest() {
        final Product product = prepareProduct();
        product.setManufactureDate(null);

        productService.create(product);

        assertNotNull(product.getId());
        assertNotNull(product.getManufactureDate());
    }

    @Test
    public void findIfExistsTest() {
        final Product product = productService.create(prepareProduct());

        final Product foundProduct = productService.find(product.getId());

        assertEquals(product, foundProduct);
    }
}