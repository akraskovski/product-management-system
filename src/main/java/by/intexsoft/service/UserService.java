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

    User update(Integer id, User object);

    void delete(Integer id);
}