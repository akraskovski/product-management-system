package by.intexsoft.service;

import by.intexsoft.model.User;
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
     * @param id
     * @return {@link User}
     */
    User find(Integer id);

    /**
     * Find one first user by username
     * @return copy of {@link User}
     */
    User findByUsername(String username);

    /**
     * Find all {@link User}s in database
     * @return list of {@link User} from database
     */
    List<User> findAll();

    /**
     * Update information about {@link User} in database
     * @param id
     * @param object
     * object - user model
     * @return {@link User} with changed data
     */
    User update(User object);

    /**
     * Delete {@link User} from database by identifier
     * @param id
     */
    void delete(Integer id);
}