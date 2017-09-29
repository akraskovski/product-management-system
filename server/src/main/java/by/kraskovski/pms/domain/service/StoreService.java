package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.Store;

/**
 * Service for {@link Store}
 */
public interface StoreService extends AbstractService<Store> {

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

    /**
     * Update information about {@link Store} in database
     */
    Store update(Store object);
}
