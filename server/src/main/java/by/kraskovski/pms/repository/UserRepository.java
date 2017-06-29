package by.kraskovski.pms.repository;

import by.kraskovski.pms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO repository for working with {@link User}.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * find {@link User} from database by name
     */
    User findByUsername(String username);
}
