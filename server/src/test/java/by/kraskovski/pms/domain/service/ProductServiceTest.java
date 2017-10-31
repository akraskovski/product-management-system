package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.Product;
import by.kraskovski.pms.domain.repository.ProductRepository;
import by.kraskovski.pms.domain.service.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.FileNotFoundException;

import static by.kraskovski.pms.utils.TestUtils.prepareProduct;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.anyObject;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private FileService imageService;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void createTest() {
        final Product product = prepareProduct();
        when(productRepository.save(product)).thenReturn(product);

        assertNotEquals(0, productService.create(new Product()).getId());
        verify(productRepository).save(product);
    }

    @Test
    public void findByIdTest() {
        final Product product = prepareProduct();
        when(productRepository.findOne(product.getId())).thenReturn(product);

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
    public void deleteTest() throws FileNotFoundException {
        final Product product = prepareProduct();
        when(productRepository.findOne(anyString())).thenReturn(product);

        productService.delete(product.getId());

        verify(imageService).delete(anyString());
        verify(productRepository).delete((Product) anyObject());
    }
}