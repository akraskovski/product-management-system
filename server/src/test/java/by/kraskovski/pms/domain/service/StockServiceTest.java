package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.model.Product;
import by.kraskovski.pms.domain.model.ProductStock;
import by.kraskovski.pms.domain.model.Stock;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.domain.model.enums.AuthorityEnum;
import by.kraskovski.pms.domain.repository.StockRepository;
import by.kraskovski.pms.domain.service.stock.DefaultStockService;
import by.kraskovski.pms.domain.service.stock.ProductStockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;

import java.util.UUID;

import static by.kraskovski.pms.utils.TestUtils.prepareProduct;
import static by.kraskovski.pms.utils.TestUtils.prepareStock;
import static by.kraskovski.pms.utils.TestUtils.prepareUserWithRole;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.anyObject;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private ProductService productService;

    @Mock
    private ProductStockService productStockService;

    @Mock
    private UserService userService;

    @InjectMocks
    private DefaultStockService stockService;

    @Test
    public void createTest() {
        final Stock stock = prepareStock();
        final User manager = prepareUserWithRole(new Authority(AuthorityEnum.ROLE_STOCK_MANAGER));
        manager.setId(UUID.randomUUID().toString());
        stock.setId(UUID.randomUUID().toString());
        when(userService.getCurrentUser()).thenReturn(manager);
        when(userService.find(anyString())).thenReturn(manager);
        when(stockRepository.save((Stock) anyObject())).thenReturn(stock);

        assertNotNull(stockService.create(new Stock()).getId());
        verify(stockRepository).save((Stock) anyObject());
    }

    @Test
    public void addNewProductPositiveTest() {
        when(stockRepository.findOne(anyString())).thenReturn(prepareStock());
        when(productService.find(anyString())).thenReturn(prepareProduct());
        when(productStockService.findByStockIdAndProductId(anyString(), anyString()))
                .thenThrow(new EntityNotFoundException());

        stockService.addProduct(random(40), random(40), nextInt());

        verify(stockRepository).findOne(anyString());
        verify(productService).find(anyString());
        verify(stockRepository).save((Stock) anyObject());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNewProductNegativeTest() {
        when(stockRepository.findOne(anyString())).thenReturn(prepareStock());
        when(productService.find(anyString())).thenReturn(prepareProduct());
        when(productStockService.findByStockIdAndProductId(anyString(), anyString()))
                .thenThrow(new EntityNotFoundException());

        stockService.addProduct(random(40), random(40), -5);

        verify(stockRepository).findOne(anyString());
        verify(productService).find(anyString());
        verify(stockRepository, times(0)).save((Stock) anyObject());
    }

    @Test
    public void addExistingProductPositiveTest() {
        final Product product = prepareProduct();
        final Stock stock = prepareStock();
        final ProductStock productStock = new ProductStock(product, stock, nextInt());
        stock.getProductStocks().add(productStock);
        when(stockRepository.findOne(anyString())).thenReturn(stock);
        when(productStockService.findByStockIdAndProductId(anyString(), anyString())).thenReturn(productStock);

        stockService.addProduct(random(40), random(40), nextInt());

        verify(stockRepository).findOne(anyString());
        verify(stockRepository).save((Stock) anyObject());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addExistingProductNegativeTest() {
        final Product product = prepareProduct();
        final Stock stock = prepareStock();
        final ProductStock productStock = new ProductStock(product, stock, nextInt());
        stock.getProductStocks().add(productStock);
        when(stockRepository.findOne(anyString())).thenReturn(stock);
        when(productStockService.findByStockIdAndProductId(anyString(), anyString())).thenReturn(productStock);

        stockService.addProduct(random(40), random(40), -5);

        verify(stockRepository).findOne(anyString());
        verify(stockRepository, times(0)).save((Stock) anyObject());
    }

    @Test
    public void deleteProductPositiveTest() {
        final Product product = prepareProduct();
        final Stock stock = prepareStock();
        final ProductStock productStock = new ProductStock(product, stock, nextInt());
        stock.getProductStocks().add(productStock);
        when(stockRepository.findOne(anyString())).thenReturn(stock);
        when(productStockService.findByStockIdAndProductId(anyString(), anyString())).thenReturn(productStock);

        stockService.deleteProduct(stock.getId(), product.getId(), 10);
        verify(stockRepository).save(stock);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteProductNegativeTest() {
        final Product product = prepareProduct();
        product.setId(random(20));
        final Stock stock = prepareStock();
        stock.setId(random(20));
        when(productStockService.findByStockIdAndProductId(anyString(), anyString()))
                .thenThrow(new EntityNotFoundException());

        stockService.deleteProduct(stock.getId(), product.getId(), 10);
    }
}