package by.kraskovski.service;

import by.kraskovski.model.Store;

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
    Store find(int id);

    /**
     * Find one first store by name
     */
    Store findByName(String name);

    /**
     * Find all {@link Store}s in database
     */
    List<Store> findAll();

    /**
     * Update information about {@link Store} in database
     */
    Store update(Store object);

    /**
     * Delete {@link Store} from database by identifier
     */
    void delete(int id);
}
