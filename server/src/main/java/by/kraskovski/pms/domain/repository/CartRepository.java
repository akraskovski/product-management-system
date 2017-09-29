package by.kraskovski.pms.domain.repository;

import by.kraskovski.pms.domain.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO repository for working with {@link Cart}.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
}
