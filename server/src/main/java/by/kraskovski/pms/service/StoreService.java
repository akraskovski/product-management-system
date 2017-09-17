package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.model.Store;

import java.util.List;

/**
 * Service for {@link Store}
 */
public interface StoreService {

    /**
     * Save {@link Store} entity to database table
     */
    Store create(Store object);

    /**
     * Find {@link Store} in database by identifier
     */
    Store find(String id);

    /**
     * Find one first store by name
     */
    Store findByName(String name);

    /**
     * Find all {@link Store}s in database
     */
    List<Store> findAll();

    /**
     * Add relation between stock and store
     */
    boolean addStock(String storeId, String stockId);

    /**
     * Delete relation between stock and store
     */
    boolean deleteStock(String storeId, String stockId);

    /**
     * Update information about {@link Store} in database
     */
    Store update(Store object);

    /**
     * Delete {@link Store} from database by identifier
     */
    void delete(String id);

    /**
     * Delete all {@link Store} from database
     */
    void deleteAll();
}
