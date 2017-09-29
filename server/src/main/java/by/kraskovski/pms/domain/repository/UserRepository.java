package by.kraskovski.pms.domain.repository;

import by.kraskovski.pms.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO repository for working with {@link User}.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * find {@link User} from database by name
     */
    User findByUsername(String username);
}
