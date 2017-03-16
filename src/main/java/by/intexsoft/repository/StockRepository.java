package by.intexsoft.repository;

import by.intexsoft.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO repository for working with {@link Stock}.
 */
public interface StockRepository extends JpaRepository<Stock, Integer>{

    /**
     * find {@link Stock} from database by specialize
     * @param specialize
     * @return {@link Stock}
     */
    Stock findBySpecialize(String specialize);
}
