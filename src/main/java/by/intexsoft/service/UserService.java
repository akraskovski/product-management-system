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
     * Find all {@link User}s in database
     * @return list of {@link User} from database
     */
    List<User> findAll();

    /**
     * Find one first user by username
     * @return copy of {@link User}
     */
    User findByUsername(String username);

}