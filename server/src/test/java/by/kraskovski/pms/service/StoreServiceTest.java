package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.Store;
import by.kraskovski.pms.repository.StoreRepository;
import by.kraskovski.pms.service.impl.StoreServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static by.kraskovski.pms.utils.TestUtils.prepareStore;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private ImageService imageService;

    @InjectMocks
    private StoreServiceImpl storeService;

    @Test
    public void createTest() {
        final Store store = prepareStore();
        when(storeRepository.save((Store) anyObject())).thenReturn(store);

        assertNotEquals(0, storeService.create(new Store()).getId());
        verify(storeRepository).save((Store) anyObject());
    }
}
