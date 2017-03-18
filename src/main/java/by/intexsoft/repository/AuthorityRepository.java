package by.intexsoft.repository;

import by.intexsoft.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * DAO repository for working with {@link Authority}.
 */
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {

    /**
     * find {@link Authority} from database by name
     * @param name of role
     * @return {@link Authority}
     */
    List<Authority> findByName(String name);
}
