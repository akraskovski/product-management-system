package by.intexsoft.repository;

import by.intexsoft.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByName(String name);

    Product findByType(String type);
}
