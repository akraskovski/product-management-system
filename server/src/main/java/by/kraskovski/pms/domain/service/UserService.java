package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.domain.model.enums.AuthorityEnum;

import java.util.List;

/**
 * Service for {@link User}
 */
public interface UserService extends CRUDService<User> {

    /**
     * Find one first user by username.
     */
    User findByUsername(String username);

    /**
     * Find users by {@link AuthorityEnum}.
     */
    List<User> findByRole(AuthorityEnum role);

    /**
     * Load and return current user from context.
     */
    User getCurrentUser();
}
