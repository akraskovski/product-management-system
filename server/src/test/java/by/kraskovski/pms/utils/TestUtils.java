package by.kraskovski.pms.utils;

import by.kraskovski.pms.domain.Product;
import by.kraskovski.pms.domain.ProductStock;
import by.kraskovski.pms.domain.Stock;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalTime;

public interface TestUtils {

    static Product prepareProduct() {
        final Product product = new Product();
        product.setId(RandomUtils.nextInt());
        product.setName(RandomStringUtils.random(20));
        product.setType(RandomStringUtils.random(20));
        product.setImage(RandomStringUtils.random(40));
        return product;
    }

    static Stock prepareStock() {
        final Stock stock = new Stock();
        stock.setId(RandomUtils.nextInt());
        stock.setAddress("Grodno, ul. Ulica");
        stock.setOpenTime(LocalTime.of(9, 0));
        stock.setCloseTime(LocalTime.of(22, 30));
        stock.setSpecialize("Products");
        stock.getProductStocks().add(new ProductStock(new Product(), stock, RandomUtils.nextInt()));
        return stock;
    }
}
