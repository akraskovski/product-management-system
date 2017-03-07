package by.intexsoft.repository;

import by.intexsoft.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer>{

    Stock findBySpecialize(String specialize);
}
