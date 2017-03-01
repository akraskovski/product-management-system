package by.intexsoft.repository;

import by.intexsoft.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Providing a level of communication with database
 * {@link org.springframework.data.repository.Repository} for {@link User}
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * find {@link User} from database by name
     * @param username of user
     * @return {@link User}
     */
    @Query("select u from User u where u.username = :username")
    User findUserByUsername(@Param("username") String username);
}
