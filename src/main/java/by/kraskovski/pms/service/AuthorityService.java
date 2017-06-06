package by.kraskovski.pms.service;

import by.kraskovski.pms.model.Authority;

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
