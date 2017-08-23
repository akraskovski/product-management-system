package by.kraskovski.pms.utils;

import by.kraskovski.pms.domain.Cart;
import by.kraskovski.pms.domain.Product;
import by.kraskovski.pms.domain.ProductStock;
import by.kraskovski.pms.domain.Stock;
import by.kraskovski.pms.domain.Store;
import by.kraskovski.pms.domain.User;

import java.time.LocalTime;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public interface TestUtils {

    static Product prepareProduct() {
        final Product product = new Product();
        product.setId(nextInt());
        product.setName(random(20));
        product.setType(random(20));
        product.setImage(random(40));
        return product;
    }

    static Stock prepareStock() {
        final Stock stock = new Stock();
        stock.setId(nextInt());
        stock.setAddress("Grodno, ul. Ulica");
        stock.setOpenTime(LocalTime.of(9, 0));
        stock.setCloseTime(LocalTime.of(22, 30));
        stock.setSpecialize("Products");
        stock.getProductStocks().add(new ProductStock(new Product(), stock, 10));
        return stock;
    }

    static Store prepareStore() {
        final Store store = new Store();
        store.setId(nextInt());
        store.setName(random(20));
        store.setAddress(random(20));
        store.setDetails(random(20));
        store.setDiscounts(true);
        store.setLogo(random(20));
        store.setMail(random(20));
        store.setPhone(random(20));
        store.setSkype(random(20));
        store.getStockList().add(prepareStock());
        return store;
    }

    static User prepareUser() {
        final User user = new User();
        user.setId(nextInt());
        user.setUsername(random(20));
        user.setPassword(random(20));
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
