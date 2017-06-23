package by.kraskovski.pms.repository;

import by.kraskovski.pms.Application;
import by.kraskovski.pms.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@DataJpaTest
@TestPropertySource("classpath:/application-test.properties")
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void createProductTest() {
        Product product = prepareProduct();
        productRepository.save(product);
        assertNotNull(entityManager.find(Product.class, product.getId()));
    }

    private Product prepareProduct() {
        Product product = new Product();
        product.setName("Product");
        product.setCost(420.5);
        product.setDetails("Description of product details");
        product.setHeight(391);
        product.setWidth(102);
        product.setWeight(10);
        product.setType("Product Type");
        product.setImage("--null--");
        product.setManufactureDate(LocalDateTime.now());
        return product;
    }
}
