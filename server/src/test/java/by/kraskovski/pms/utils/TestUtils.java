package by.kraskovski.pms.utils;

import by.kraskovski.pms.domain.Authority;
import by.kraskovski.pms.domain.Cart;
import by.kraskovski.pms.domain.Product;
import by.kraskovski.pms.domain.ProductStock;
import by.kraskovski.pms.domain.Stock;
import by.kraskovski.pms.domain.Store;
import by.kraskovski.pms.domain.User;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextDouble;

public final class TestUtils {

    private TestUtils() {
    }

    public static Product prepareProduct() {
        final Product product = new Product();
        product.setName(random(20));
        product.setType(random(20));
        product.setImage(random(40));
        product.setCost(nextDouble());
        product.setDetails(random(50));
        product.setHeight(nextDouble());
        product.setWidth(nextDouble());
        product.setWeight(nextDouble());
        return product;
    }

    public static Stock prepareStock() {
        final Stock stock = new Stock();
        stock.setSpecialize(random(20));
        stock.setAddress(random(20));
        stock.setPhone(random(20));
        stock.setSquare(nextDouble());
//        stock.setOpenTime(LocalTime.of(9, 30));
//        stock.setCloseTime(LocalTime.of(22, 0));
        return stock;
    }

    public static Store prepareStore() {
        final Store store = new Store();
        store.setName(random(20));
        store.setAddress(random(20));
        store.setDetails(random(20));
        store.setDiscounts(true);
        store.setLogo(random(20));
        store.setMail(random(20));
        store.setPhone(random(20));
        store.setSkype(random(20));
        return store;
    }

    public static User prepareUser() {
        final User user = new User();
        user.setUsername(random(20));
        user.setPassword(random(20));
        return user;
    }

    public static User prepareUserWithRole(final Authority authority) {
        final User user = new User();
        user.getAuthorities().add(authority);
        user.setUsername(random(20));
        user.setPassword(random(20));
        user.addCart(new Cart());
        return user;
    }

    public static Cart prepareCart(final User user) {
        final Cart cart = new Cart();
        cart.setUser(user);
        return cart;
    }

    public static ProductStock prepareProductStock() {
        return new ProductStock(prepareProduct(), prepareStock(), 10);
    }
}
