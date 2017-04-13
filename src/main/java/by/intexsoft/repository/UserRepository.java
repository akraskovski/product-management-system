package by.intexsoft.repository;

import by.intexsoft.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * DAO repository for working with {@link User}.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * find {@link User} from database by name
     */
    User findByUsername(String username);
}
