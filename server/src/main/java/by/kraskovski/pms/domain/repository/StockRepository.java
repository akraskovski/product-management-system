package by.kraskovski.pms.domain.repository;

import by.kraskovski.pms.domain.model.Stock;
import by.kraskovski.pms.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO repository for working with {@link Stock}.
 */
@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

    /**
     * Find {@link Stock} from database by specialize.
     */
    List<Stock> findBySpecialize(String specialize);

    /**
     * Find manager related {@link Stock}s.
     */
    List<Stock> findAllByManager(User manager);
}
