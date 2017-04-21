package by.intexsoft;

import by.intexsoft.model.Product;
import by.intexsoft.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    private static Product product;

    @Before
    public void testCreate() {
        product = new Product();
        product.setName("Tested_Product");
        product.setCost(232.22);
        product.setType("Food");
        productService.create(product);
    }

    @Test
    public void testForNullService() {
        assertNotNull(productService);
    }

    @Test
    public void testFindAll() {
        assertNotNull(productService.findAll());
    }

    @After
    public void testDelete() {
        productService.delete(product.id);
    }
}
