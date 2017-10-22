package by.kraskovski.pms.utils;

import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.model.Cart;
import by.kraskovski.pms.domain.model.Product;
import by.kraskovski.pms.domain.model.Stock;
import by.kraskovski.pms.domain.model.Store;
import by.kraskovski.pms.domain.model.User;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.commons.lang3.RandomUtils.nextDouble;

public final class TestUtils {

    private TestUtils() {
    }

    public static Product prepareProduct() {
        final Product product = new Product();
        product.setName(randomAlphabetic(20));
        product.setType(randomAlphabetic(20));
        product.setImage(randomAlphabetic(40));
        product.setCost(10.90);
        product.setDetails(randomAlphabetic(50));
        product.setHeight(nextDouble());
        product.setWidth(nextDouble());
        product.setWeight(nextDouble());
        product.setManufactureDate(LocalDateTime.now());
        return product;
    }

    public static Stock prepareStock() {
        final Stock stock = new Stock();
        stock.setSpecialize(randomAlphabetic(20));
        stock.setAddress(randomAlphabetic(20));
        stock.setPhone(randomNumeric(20));
        stock.setSquare(nextDouble());
        stock.setOpenTime(LocalTime.of(9, 30));
        stock.setCloseTime(LocalTime.of(22, 0));
        return stock;
    }

    public static Store prepareStore() {
        final Store store = new Store();
        store.setName(randomAlphabetic(20));
        store.setAddress(randomAlphabetic(20));
        store.setDetails(randomAlphabetic(20));
        store.setDiscounts(true);
        store.setLogo(randomAlphabetic(20));
        store.setMail(randomAlphabetic(10) + "@gmail.com");
        store.setPhone(randomNumeric(20));
        store.setSkype(randomAlphabetic(20));
        return store;
    }

    public static User prepareUser() {
        final User user = new User();
        user.setUsername(randomAlphabetic(20));
        user.setPassword(randomAlphabetic(5) + randomNumeric(5));
        user.setEmail(randomAlphabetic(10) + "@gmail.com");
        user.setFirstName(randomAlphabetic(20));
        user.setLastName(randomAlphabetic(20));
        user.setPhone(randomNumeric(10));
        return user;
    }

    public static User prepareUserWithRole(final Authority authority) {
        final User user = prepareUser();
        user.getAuthorities().add(authority);
        user.addCart(new Cart());
        return user;
    }
}
