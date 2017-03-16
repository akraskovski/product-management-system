package by.intexsoft.service.impl;

import by.intexsoft.model.Product;
import by.intexsoft.repository.ProductRepository;
import by.intexsoft.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product object) {
        return productRepository.save(object);
    }

    @Override
    public Product find(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Integer id, Product object) {
        return productRepository.save(object);
    }

    @Override
    public void delete(Integer id) {
        productRepository.delete(id);
    }
}
