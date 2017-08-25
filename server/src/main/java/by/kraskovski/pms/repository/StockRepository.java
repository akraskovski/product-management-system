package by.kraskovski.pms.repository;

import by.kraskovski.pms.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * DAO repository for working with {@link Stock}.
 */
public interface StockRepository extends JpaRepository<Stock, String> {

    /**
     * find {@link Stock} from database by specialize
     */
    List<Stock> findBySpecialize(String specialize);
}
