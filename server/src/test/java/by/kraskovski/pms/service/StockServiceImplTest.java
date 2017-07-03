package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.Product;
import by.kraskovski.pms.domain.ProductStock;
import by.kraskovski.pms.domain.Stock;
import by.kraskovski.pms.repository.StockRepository;
import by.kraskovski.pms.service.impl.StockServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalTime;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class
)
public class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private StockServiceImpl stockService;

    private Stock prepareStock() {
        final Stock stock = new Stock();
        stock.setId(RandomUtils.nextInt());
        stock.setAddress("Grodno, ul. Ulica");
        stock.setOpenTime(LocalTime.of(9, 0));
        stock.setCloseTime(LocalTime.of(22, 30));
        stock.setSpecialize("Products");
        stock.getProductStocks().add(new ProductStock(new Product(), stock, RandomUtils.nextInt()));
        return stock;
    }

    @Test
    public void createTest() {
        final Stock stock = prepareStock();
        when(stockRepository.save((Stock) anyObject())).thenReturn(stock);

        Assert.assertNotNull(stockService.create(new Stock()).getId());
        verify(stockRepository).save((Stock) anyObject());
    }
}