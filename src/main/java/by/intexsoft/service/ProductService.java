package by.intexsoft.service;

import by.intexsoft.model.Product;

import java.util.List;

public interface ProductService {

    Product create(Product object);

    Product find(Integer id);

    List<Product> findByName(String name);

    Product findByType(String type);

    List<Product> findAll();

    Product update(Integer id, Product object);

    void delete(Integer id);
}
