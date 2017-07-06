package by.kraskovski.pms.utils;

import by.kraskovski.pms.domain.*;
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
        stock.getProductStocks().add(new ProductStock(new Product(), stock, 10));
        return stock;
    }

    static User prepareUser() {
        final User user = new User();
        user.setId(RandomUtils.nextInt());
        user.setUsername(RandomStringUtils.random(20));
        user.setPassword(RandomStringUtils.random(20));
        user.addCart(new Cart());
        return user;
    }

    static Cart prepareCart() {
        final Cart cart = new Cart();
        cart.setUser(new User());
        return cart;
    }

    static ProductStock prepareProductStock() {
        return new ProductStock(prepareProduct(), prepareStock(), 10);
    }
}
