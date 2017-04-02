package by.intexsoft.service;

import by.intexsoft.model.Store;

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
     *
     * @param id
     * @return {@link Store}
     */
    Store find(Integer id);

    /**
     * Find one first store by name
     *
     * @return copy of {@link Store}
     */
    Store findByName(String name);

    /**
     * Find all {@link Store}s in database
     *
     * @return list of {@link Store} from database
     */
    List<Store> findAll();

    /**
     * Update information about {@link Store} in database
     *
     * @param object
     * @return {@link Store} with changed data
     */
    Store update(Store object);

    /**
     * Delete {@link Store} from database by identifier
     *
     * @param id
     */
    void delete(Integer id);

}
