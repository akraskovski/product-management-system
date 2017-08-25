package by.kraskovski.pms.repository;

import by.kraskovski.pms.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * DAO repository for working with {@link Product}.
 */
public interface ProductRepository extends JpaRepository<Product, String> {

    /**
     * find {@link Product}s from database by name
     */
    @Query("SELECT p FROM Product p WHERE "
            + "p.name LIKE '%' || :searchString || '%'")
    List<Product> findByName(@Param("searchString") String searchString);

    /**
     * find {@link Product}s from database by type
     */
    @Query("SELECT p FROM Product p WHERE "
            + "p.type LIKE '%' || :searchString || '%'")
    List<Product> findByType(@Param("searchString") String searchString);
}
