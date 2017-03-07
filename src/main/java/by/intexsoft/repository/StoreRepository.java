package by.intexsoft.repository;

import by.intexsoft.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    Store findByName(String name);
}
