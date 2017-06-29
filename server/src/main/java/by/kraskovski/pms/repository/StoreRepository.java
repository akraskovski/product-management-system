package by.kraskovski.pms.repository;

import by.kraskovski.pms.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO repository for working with {@link Store}.
 */
public interface StoreRepository extends JpaRepository<Store, Integer> {

    /**
     * find {@link Store} from database by name
     */
    Store findByName(String name);
}
