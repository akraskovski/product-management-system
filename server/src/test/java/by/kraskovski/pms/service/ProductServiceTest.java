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

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ImageService imageService;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product prepareProduct() {
        final Product product = new Product();
        product.setId(RandomUtils.nextInt());
        product.setName(RandomStringUtils.random(20));
        product.setType(RandomStringUtils.random(20));
        product.setImage(RandomStringUtils.random(40));
        return product;
    }

    @Test
    public void createTest() {
        final Product product = prepareProduct();
        when(productRepository.save((Product) anyObject())).thenReturn(product);

        assertNotEquals(productService.create(new Product()).getId(), 0);
        verify(productRepository).save((Product) anyObject());
    }

    @Test
    public void findByIdTest() {
        final Product product = prepareProduct();
        when(productRepository.findOne(anyInt())).thenReturn(product);

        assertEquals(product, productService.find(product.getId()));
        verify(productRepository).findOne(product.getId());
    }

    @Test
    public void findByNameTest() {
        final Product product = prepareProduct();
        when(productRepository.findByName(anyString())).thenReturn(singletonList(product));

        assertEquals(singletonList(product), productService.findByName(product.getName()));
        verify(productRepository).findByName(anyString());
    }

    @Test
    public void findByTypeTest() {
        final Product product = prepareProduct();
        when(productRepository.findByType(anyString())).thenReturn(singletonList(product));

        assertEquals(singletonList(product), productService.findByType(product.getType()));
        verify(productRepository).findByType(anyString());
    }

    @Test
    public void deleteTest() {
        final Product product = prepareProduct();
        when(imageService.delete(anyString())).thenReturn(true);
        when(productRepository.findOne(anyInt())).thenReturn(product);

        productService.delete(product.getId());

        verify(imageService).delete(anyString());
        verify(productRepository).delete((Product) anyObject());
    }
}