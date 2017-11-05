package by.kraskovski.pms.domain.service.integration;

import by.kraskovski.pms.domain.model.Stock;
import by.kraskovski.pms.domain.model.Store;
import by.kraskovski.pms.domain.service.StockService;
import by.kraskovski.pms.domain.service.StoreService;
import by.kraskovski.pms.domain.service.integration.config.ServiceTestConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import static by.kraskovski.pms.utils.TestUtils.prepareStock;
import static by.kraskovski.pms.utils.TestUtils.prepareStore;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertTrue;

/**
 * Integration test for {@link StoreService}.
 */
public class StoreServiceIT extends ServiceTestConfig {

    @Autowired
    private StoreService storeService;

    @Autowired
    private StockService stockService;

    @Before
    public void setUp() {
        stockService.deleteAll();
        storeService.deleteAll();
    }

    @After
    public void cleanUp() {
        storeService.deleteAll();
        stockService.deleteAll();
    }

    @Test
    @Transactional
    public void addStockPositiveTest() {
        final Stock stock = stockService.create(prepareStock());
        final Store store = storeService.create(prepareStore());

        storeService.addStock(store.getId(), stock.getId());

        final Store foundStore = storeService.find(store.getId());
        assertTrue(foundStore.getStockList().contains(stock));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addStockIfAlreadyExistsTest() {
        final Stock stock = stockService.create(prepareStock());
        final Store store = prepareStore();
        store.getStockList().add(stock);
        storeService.create(store);

        storeService.addStock(store.getId(), stock.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void addStockIfNotExistsInDbTest() {
        final Store store = storeService.create(prepareStore());

        storeService.addStock(store.getId(), randomAlphabetic(20));
    }

    @Test(expected = EntityNotFoundException.class)
    public void addStockIfStoreNotExistsInDbTest() {
        final Stock stock = stockService.create(prepareStock());

        storeService.addStock(randomAlphabetic(20), stock.getId());
    }
}
