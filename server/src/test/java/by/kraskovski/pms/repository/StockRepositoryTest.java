package by.kraskovski.pms.repository;

import by.kraskovski.pms.domain.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Integration test for {@link StockRepository}
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class StockRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StockRepository stockRepository;

    @Test
    public void findBySpecializeTest() {
        entityManager.persist(prepareStock());
        final List<Stock> stocks = stockRepository.findBySpecialize("Phones");
        assertEquals(stocks.size(), 1);
        assertEquals(stocks.get(0).getSpecialize(), "Phones");
    }

    private Stock prepareStock() {
        final Stock stock = new Stock();
        stock.setSpecialize("Phones");
        stock.setAddress("Grodno, ul. Ulica");
        stock.setSquare(20.2);
        stock.setPhone("80292929239");
        stock.setOpenTime(LocalTime.of(9, 30));
        stock.setCloseTime(LocalTime.of(23, 0));
        return stock;
    }
}
