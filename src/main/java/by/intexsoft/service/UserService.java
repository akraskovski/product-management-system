package by.intexsoft.service;

import by.intexsoft.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    /**
     * @return list of {@link User} from database.
     */
    List<User> findAllUsers();

    /**
     * Find one first user by name.
     * @param name
     * @return copy of {@link User}
     */
    User findUser(String name);

    /**
     * Save {@link User} entity to database table.
     * @param user
     */
    void saveUser(User user);
}