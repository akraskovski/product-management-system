package by.kraskovski.service;

import by.kraskovski.model.Authority;

import java.util.List;

/**
 * Service for {@link Authority}
 */
public interface AuthorityService {

    /**
     * Find authority in database by id
     */
    Authority find(int id);

    /**
     * Find all authoroties in database
     */
    List<Authority> findAll();
}
