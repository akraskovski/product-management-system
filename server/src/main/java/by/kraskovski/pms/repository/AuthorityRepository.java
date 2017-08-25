package by.kraskovski.pms.repository;

import by.kraskovski.pms.domain.Authority;
import by.kraskovski.pms.domain.enums.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO repository for working with {@link Authority}.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {

    Authority findByAuthority(AuthorityEnum authority);
}
