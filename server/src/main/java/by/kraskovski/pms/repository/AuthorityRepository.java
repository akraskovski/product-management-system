package by.kraskovski.pms.repository;

import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.enums.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO repository for working with {@link Authority}.
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

    Authority findByAuthority(AuthorityEnum authority);
}
