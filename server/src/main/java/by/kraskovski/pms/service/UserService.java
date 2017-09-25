package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.model.User;

import java.util.List;

/**
 * Service for {@link User}
 */
public interface UserService extends AbstractService<User> {

    /**
     * Find one first user by username
     */
    User findByUsername(String username);
    /**
     * Update information about {@link User} in database
     */
    User update(User object);
}
