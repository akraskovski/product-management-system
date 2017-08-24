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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

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
        when(storeRepository.save((Store) anyObject())).thenReturn(prepareStore());

        assertNotEquals(0, storeService.create(new Store()).getId());
        verify(storeRepository).save((Store) anyObject());
    }

    @Test
    public void findByIdTest() {
        final Store store = prepareStore();
        when(storeRepository.findOne(anyInt())).thenReturn(store);

        assertEquals(store, storeService.find(store.getId()));
        verify(storeRepository).findOne(anyInt());
    }

    @Test
    public void findByNameTest() {
        final Store store = prepareStore();
        when(storeRepository.findByName(anyString())).thenReturn(store);

        assertEquals(store, storeService.findByName(store.getName()));
        verify(storeRepository).findByName(anyString());
    }

    @Test
    public void deleteStoreWithLogoTest() {
        final Store store = prepareStore();
        when(storeRepository.findOne(anyInt())).thenReturn(store);
        when(imageService.delete(anyString())).thenReturn(true);

        storeService.delete(store.getId());

        verify(storeRepository).findOne(store.getId());
        verify(imageService).delete(store.getLogo());
        verify(storeRepository).delete((Store) anyObject());
    }

    @Test
    public void deleteStoreWithoutLogoTest() {
        final Store store = prepareStore();
        store.setLogo(org.apache.commons.lang3.StringUtils.EMPTY);
        when(storeRepository.findOne(anyInt())).thenReturn(store);

        storeService.delete(store.getId());

        verify(storeRepository).findOne(store.getId());
        verifyZeroInteractions(imageService);
        verify(storeRepository).delete((Store) anyObject());
    }
}
