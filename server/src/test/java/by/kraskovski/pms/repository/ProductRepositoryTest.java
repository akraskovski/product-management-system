package by.kraskovski.pms.repository;

import by.kraskovski.pms.domain.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static by.kraskovski.pms.utils.TestUtils.prepareProduct;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
        final Product product = entityManager.persist(prepareProduct());
        final Product foundProduct = productRepository.findByName(product.getName().substring(0, 3)).get(0);
        assertNotNull(foundProduct);
        assertTrue(foundProduct.getName().contains(product.getName()));
    }

    @Test
    public void findByTypeTest() {
        final Product product = entityManager.persist(prepareProduct());
        final List<Product> foundProducts = productRepository.findByType(product.getType().substring(0, 3));
        assertEquals(foundProducts.size(), 1);
        assertEquals(foundProducts.get(0).getType(), product.getType());
    }
}
