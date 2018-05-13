package by.kraskovski.pms.domain.repository;

import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.domain.model.enums.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO repository for working with {@link User}.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * find {@link User} from database by name
     */
    User findByUsername(String username);

    /**
     * find {@link User} from database by role
     */
    List<User> findByAuthorityName(AuthorityEnum name);
}
