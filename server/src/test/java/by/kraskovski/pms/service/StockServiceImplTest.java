package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.Product;
import by.kraskovski.pms.domain.ProductStock;
import by.kraskovski.pms.domain.Stock;
import by.kraskovski.pms.repository.StockRepository;
import by.kraskovski.pms.service.impl.StockServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static by.kraskovski.pms.utils.TestUtils.prepareProduct;
import static by.kraskovski.pms.utils.TestUtils.prepareStock;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    public void createTest() {
        final Stock stock = prepareStock();
        when(stockRepository.save((Stock) anyObject())).thenReturn(stock);

        assertNotNull(stockService.create(new Stock()).getId());
        verify(stockRepository).save((Stock) anyObject());
    }

    @Test
    public void addNewProductTest() {
        when(stockRepository.findOne(anyInt())).thenReturn(prepareStock());
        when(productService.find(anyInt())).thenReturn(prepareProduct());

        assertTrue(stockService.addProduct(nextInt(), nextInt(), nextInt()));
        verify(stockRepository).findOne(anyInt());
        verify(productService).find(anyInt());
        verify(stockRepository).save((Stock) anyObject());
    }

    @Test
    public void addExistingProductTest() {
        final Product product = prepareProduct();
        final Stock stock = prepareStock();
        stock.getProductStocks().add(new ProductStock(product, stock, nextInt()));
        when(stockRepository.findOne(anyInt())).thenReturn(stock);
        when(productService.find(anyInt())).thenReturn(product);

        assertTrue(stockService.addProduct(nextInt(), nextInt(), nextInt()));
        verify(stockRepository).findOne(anyInt());
        verify(productService).find(anyInt());
        verify(stockRepository).save((Stock) anyObject());
    }

    @Test
    public void deleteProductPositiveTest() {
        final Product product = prepareProduct();
        final Stock stock = prepareStock();
        Set<ProductStock> productStocks = new HashSet<>();
        productStocks.add((new ProductStock(product, stock, 10)));
        stock.setProductStocks(productStocks);
        when(stockRepository.findOne(anyInt())).thenReturn(stock);
        when(productService.find(anyInt())).thenReturn(product);

        assertTrue(stockService.deleteProduct(stock.getId(), product.getId(), 10));
        verify(productService).find(product.getId());
        verify(stockRepository).save(stock);
    }

    @Test
    public void deleteProductNegativeTest() {
        final Product product = prepareProduct();
        final Stock stock = prepareStock();
        when(stockRepository.findOne(anyInt())).thenReturn(stock);
        when(productService.find(anyInt())).thenReturn(product);

        assertFalse(stockService.deleteProduct(stock.getId(), product.getId(), 10));
        verify(productService).find(product.getId());
        verify(stockRepository, never()).save(stock);
    }
}