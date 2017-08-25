package by.kraskovski.pms.repository;

import by.kraskovski.pms.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Integration test for {@link ProductRepository}
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findByNameTest() {
        entityManager.persist(prepareProduct());
        final Product foundProduct = productRepository.findByName("Sam").get(0);
        assertNotNull(foundProduct);
        assertTrue(foundProduct.getName().contains("Sam"));
    }

    @Test
    public void findByTypeTest() {
        entityManager.persist(prepareProduct());
        final List<Product> foundProducts = productRepository.findByType("Phone");
        assertEquals(foundProducts.size(), 1);
        assertEquals(foundProducts.get(0).getType(), "Phone");
    }

    private Product prepareProduct() {
        final Product product = new Product();
        product.setName("Samsung s8");
        product.setCost(599.99);
        product.setDetails("Components\n"
                + "Galaxy S8 phone\n"
                + "Earphones \n"
                + "USB Type-C Cable\n"
                + "Travel Adapter\n"
                + "USB Connector (C to A)\n"
                + "Micro USB Connector (C to B)\n"
                + "Ejection Pin\n"
                + "Ear Tips\n"
                + "Quick Start Guide ");
        product.setHeight(391);
        product.setWidth(102);
        product.setWeight(10);
        product.setType("Phone");
        product.setImage("--null--");
        product.setManufactureDate(LocalDateTime.now());
        return product;
    }
}
