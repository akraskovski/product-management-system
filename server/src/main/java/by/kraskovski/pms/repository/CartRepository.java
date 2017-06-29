package by.kraskovski.pms.repository;

import by.kraskovski.pms.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
