package by.kraskovski.pms.controller;

import by.kraskovski.pms.Application;
import by.kraskovski.pms.model.Product;
import by.kraskovski.pms.model.ProductStock;
import by.kraskovski.pms.model.Stock;
import by.kraskovski.pms.service.ProductService;
import by.kraskovski.pms.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Application.class)
public class StockControllerTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @Test
    @Transactional
    public void createStockTest() {
        Product product = fillProduct();
        productService.create(product);
        Stock stock = fillStock();
        ProductStock productStock = new ProductStock();
        productStock.product = product;
        productStock.stock = stock;
        productStock.productsCount = 10;
        stock.productStocks.add(productStock);
        stockService.create(stock);
        assertNotNull(product.getId());
        assertNotNull(stock.getId());
    }

    @Test
    @Transactional
    public void findAllStocksTest() {
        List<Stock> stocks = stockService.findAll();
        assertNotNull(stocks);
    }

    private Stock fillStock() {
        Stock stock = new Stock();
        stock.setSpecialize("Phones");
        stock.setAddress("Grodno, Lenina 23, 5");
        stock.setPhone("+375292381828");
        stock.setSquare(200);
        return stock;
    }

    private Product fillProduct() {
        Product product = new Product();
        product.setName("Iphone 2g");
        product.setCost(100);
        product.setType("Phone");
        product.setDetails("description");
        product.setWeight(300);
        return product;
    }
}