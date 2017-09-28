package by.kraskovski.pms.domain.service;

import java.util.List;

public interface AbstractService <T> {

    /**
     * Save {@link T} entity to database table.
     */
    T create(T object);

    /**
     * Find {@link T} in database by id.
     */
    T find(String id);

    /**
     * Find all {@link T} in database.
     */
    List<T> findAll();

    /**
     * Delete {@link T} by id from database.
     */
    void delete(String id);

    /**
     * Delete all {@link T} from database
     */
    void deleteAll();
}
