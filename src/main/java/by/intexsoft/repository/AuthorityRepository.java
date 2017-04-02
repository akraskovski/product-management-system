package by.intexsoft.repository;

import by.intexsoft.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO repository for working with {@link Authority}.
 */
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {

}
