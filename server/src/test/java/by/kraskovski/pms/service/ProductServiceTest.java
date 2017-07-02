package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.Product;
import by.kraskovski.pms.repository.ProductRepository;
import by.kraskovski.pms.service.impl.ProductServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ImageService imageService;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void createProductTest() {
        final Product product = new Product(RandomUtils.nextInt(), RandomStringUtils.random(20));
        doReturn(product).when(productRepository).save((Product) anyObject());
        productService.create(product);

        verify(productRepository).save(product);
        assertNotEquals(product.getId(), 0);
        assertNotNull(product.getManufactureDate());
    }
}