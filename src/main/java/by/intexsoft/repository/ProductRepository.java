package by.intexsoft.repository;

import by.intexsoft.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * DAO repository for working with {@link Product}.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * find {@link Product} from database by name
     */
    List<Product> findByName(String name);
}
