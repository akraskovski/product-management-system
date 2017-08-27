package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.Authority;
import by.kraskovski.pms.domain.enums.AuthorityEnum;

import java.util.List;

/**
 * Service for {@link Authority}
 */
public interface AuthorityService {

    /**
     * Save {@link Authority} entity to database table
     */
    Authority create(Authority object);

    /**
     * Find authority in database by id.
     */
    Authority find(String id);

    /**
     * Find authority in database by name.
     */
    Authority findByName(AuthorityEnum name);

    /**
     * Find all authoroties in database.
     */
    List<Authority> findAll();

    /**
     * Delete {@link Authority} by id from database.
     */
    void delete(String id);
}
