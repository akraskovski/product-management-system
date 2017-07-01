package by.kraskovski.pms.repository;

import by.kraskovski.pms.Application;
import by.kraskovski.pms.model.Store;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@DataJpaTest
@TestPropertySource("classpath:/application-test.properties")
public class StoreRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StoreRepository storeRepository;

    @After
    public void after() {
        storeRepository.deleteAll();
    }

    @Test
    public void findByName() {
        entityManager.persist(prepareStore());
        final Store store = storeRepository.findByName("Ali");
        assertNotNull(store);
        assertTrue(store.getName().contains("Ali"));
    }

    private Store prepareStore() {
        final Store store = new Store();
        store.setName("Aliexpress");
        store.setAddress("China");
        store.setDetails("Online-shop");
        store.setDiscounts(true);
        store.setPhone("635245824450");
        store.setSkype("skype");
        store.setMail("aliexpress@grsu.by");
        store.setLogo("--null--");
        return store;
    }
}