package by.kraskovski.pms.repository;

import by.kraskovski.pms.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO repository for working with {@link Cart}.
 */
public interface CartRepository extends JpaRepository<Cart, String> {
}
