package by.kraskovski.pms.repository;

import by.kraskovski.pms.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO repository for working with {@link Cart}.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
}
