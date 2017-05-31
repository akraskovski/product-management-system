package by.kraskovski.repository;

import by.kraskovski.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO repository for working with {@link Authority}.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
