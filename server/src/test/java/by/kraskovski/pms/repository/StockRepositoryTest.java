package by.kraskovski.pms.repository;

import by.kraskovski.pms.Application;
import by.kraskovski.pms.model.Stock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@DataJpaTest
@TestPropertySource("classpath:/application-test.properties")
public class StockRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void createStockTest() {
        final Stock stock = prepareStock();
        entityManager.persist(stock);
        Assert.assertNotNull(entityManager.find(Stock.class, stock.getId()));
    }

    private Stock prepareStock() {
        final Stock stock = new Stock();
        stock.setSpecialize("Specialize");
        stock.setAddress("Grodno, ul. Ulica");
        stock.setSquare(20.2);
        stock.setPhone("80292929239");
        stock.setOpenTime(LocalTime.of(9, 30));
        stock.setCloseTime(LocalTime.of(23, 0));
        return stock;
    }
}
