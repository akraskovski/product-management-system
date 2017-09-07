package by.kraskovski.pms.repository;

import by.kraskovski.pms.domain.model.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO repository for working with {@link ProductStock}.
 */
@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, String> {

    ProductStock findByStockIdAndProductId(String stockId, String productId);
}
