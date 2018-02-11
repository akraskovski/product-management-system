package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.base.BaseEntity;

import java.util.List;

/**
 * Abstract CRUD operations service for models.
 *
 * @param <T> any model, extends from {@link BaseEntity} with unique id.
 */
public interface CRUDService<T extends BaseEntity> {

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
     * Update information about {@link T} in database.
     */
    T update(T object);

    /**
     * Delete {@link T} by id from database.
     */
    void delete(String id);

    /**
     * Delete all {@link T} from database
     */
    void deleteAll();
}
