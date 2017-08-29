package by.kraskovski.pms.utils;

import by.kraskovski.pms.domain.*;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextDouble;

public interface TestUtils {

    static Product prepareProduct() {
        final Product product = new Product();
        product.setName(random(20));
        product.setType(random(20));
        product.setImage(random(40));
        return product;
    }

    static Stock prepareStock() {
        final Stock stock = new Stock();
        stock.setSpecialize(random(20));
        stock.setAddress(random(20));
        stock.setPhone(random(20));
        stock.setSquare(nextDouble());
//        stock.setOpenTime(LocalTime.of(9, 30));
//        stock.setCloseTime(LocalTime.of(22, 0));
        return stock;
    }

    static Store prepareStore() {
        final Store store = new Store();
        store.setId(random(40));
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
        user.setUsername(random(20));
        user.setPassword(random(20));
        user.addCart(new Cart());
        return user;
    }

    static User prepareUserWithRole(final Authority authority) {
        final User user = new User();
        user.getAuthorities().add(authority);
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
