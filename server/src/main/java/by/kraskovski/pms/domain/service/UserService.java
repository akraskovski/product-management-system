package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.User;

/**
 * Service for {@link User}
 */
public interface UserService extends CRUDService<User> {

    /**
     * Find one first user by username
     */
    User findByUsername(String username);
    /**
     * Update information about {@link User} in database
     */
    User update(User object);
}
