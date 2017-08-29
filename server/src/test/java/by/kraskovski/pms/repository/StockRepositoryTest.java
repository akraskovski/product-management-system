package by.kraskovski.pms.repository;

import by.kraskovski.pms.domain.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static by.kraskovski.pms.utils.TestUtils.prepareStock;
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
        final Stock stock = entityManager.persist(prepareStock());
        final List<Stock> foundStocks = stockRepository.findBySpecialize(stock.getSpecialize());
        assertEquals(foundStocks.size(), 1);
        assertEquals(foundStocks.get(0).getSpecialize(), stock.getSpecialize());
    }
}
