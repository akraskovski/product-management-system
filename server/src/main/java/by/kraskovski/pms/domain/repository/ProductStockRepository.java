package by.kraskovski.pms.domain.repository;

import by.kraskovski.pms.domain.model.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO repository for working with {@link ProductStock}.
 */
@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, String> {

    /**
     * Find {@link ProductStock} by {@link by.kraskovski.pms.domain.model.Stock}
     * and {@link by.kraskovski.pms.domain.model.Product} ids
     */
    ProductStock findByStockIdAndProductId(String stockId, String productId);
}
