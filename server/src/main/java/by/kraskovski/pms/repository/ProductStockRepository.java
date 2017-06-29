package by.kraskovski.pms.repository;

import by.kraskovski.pms.model.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Integer> {
}
