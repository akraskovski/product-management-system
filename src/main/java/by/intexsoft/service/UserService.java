package by.intexsoft.service;

import by.intexsoft.model.User;
import java.util.List;

/**
 * Service for {@link User}
 */
public interface UserService {

    /**
     * Find all {@link User}s in database
     * @return list of {@link User} from database
     */
    List<User> findAllUsers();

    /**
     * Find one first user by username
     * @return copy of {@link User}
     */
    User findUser(String username);

    /**
     * Save {@link User} entity to database table
     */
    void saveUser(User user);
}