package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.enums.AuthorityEnum;
import by.kraskovski.pms.domain.model.Authority;

import java.util.List;

/**
 * Service for {@link Authority}
 */
public interface AuthorityService extends AbstractService<Authority> {

    /**
     * Find authority in database by name.
     */
    Authority findByName(AuthorityEnum name);
}
