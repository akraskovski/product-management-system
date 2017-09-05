package by.kraskovski.pms.repository;

import by.kraskovski.pms.domain.model.Store;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static by.kraskovski.pms.utils.TestUtils.prepareStore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Integration test for {@link StoreRepository}
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class StoreRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void findByNameTest() {
        final Store store = entityManager.persist(prepareStore());
        final Store foundStore = storeRepository.findByName(store.getName().substring(0, 3));
        assertNotNull(foundStore);
        assertEquals(store.getName(), foundStore.getName());
    }
}