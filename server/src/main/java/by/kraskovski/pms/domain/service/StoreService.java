package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.Store;

/**
 * Service for {@link Store}
 */
public interface StoreService extends CRUDService<Store> {

    /**
     * Find one first store by name
     */
    Store findByName(String name);

    /**
     * Add relation between stock and store
     */
    void addStock(String storeId, String stockId);

    /**
     * Delete relation between stock and store
     */
    void deleteStock(String storeId, String stockId);
}
