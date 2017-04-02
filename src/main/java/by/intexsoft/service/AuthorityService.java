package by.intexsoft.service;

import by.intexsoft.model.Authority;

import java.util.List;

/**
 * Service for {@link Authority}
 */
public interface AuthorityService {

    /**
     * Find authority in database by id
     *
     * @param id
     * @return Authority
     */
    Authority find(Integer id);

    /**
     * Find all authoroties in database
     *
     * @return list of {@link Authority}'s
     */
    List<Authority> findAll();
}
