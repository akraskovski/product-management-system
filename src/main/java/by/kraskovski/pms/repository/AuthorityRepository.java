package by.kraskovski.pms.repository;

import by.kraskovski.pms.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO repository for working with {@link Authority}.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
