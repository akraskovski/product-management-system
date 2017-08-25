package by.kraskovski.pms.repository;

import by.kraskovski.pms.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * DAO repository for working with {@link Store}.
 */
public interface StoreRepository extends JpaRepository<Store, String> {

    /**
     * find {@link Store} from database by name
     */
    @Query("SELECT s FROM Store s WHERE "
            + "s.name LIKE '%' || :searchString || '%'")
    Store findByName(@Param("searchString") String searchString);
}
