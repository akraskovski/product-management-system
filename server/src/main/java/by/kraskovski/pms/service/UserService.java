package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.model.User;

import java.util.List;

/**
 * Service for {@link User}
 */
public interface UserService {

    /**
     * Save {@link User} entity to database table
     */
    User create(User object);

    /**
     * Find {@link User} in database by identifier
     */
    User find(String id);

    /**
     * Find one first user by username
     */
    User findByUsername(String username);

    /**
     * Find all {@link User}s in database
     */
    List<User> findAll();

    /**
     * Update information about {@link User} in database
     */
    User update(User object);

    /**
     * Delete {@link User} from database by identifier
     */
    void delete(String id);

    /**
     * Delete all {@link User}s from database
     */
    void deleteAll();
}
