package by.intexsoft.repository;

import by.intexsoft.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Providing a level of communication with database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Custom method to find user from database by name
     * @param name of user
     * @return {@link User}
     */
    @Query("select u from User u where u.name = :username")
    User findUserByName(@Param("username") String name);
}
