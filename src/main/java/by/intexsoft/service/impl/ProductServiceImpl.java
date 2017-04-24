package by.intexsoft.service.impl;

import by.intexsoft.model.Product;
import by.intexsoft.repository.ProductRepository;
import by.intexsoft.service.ImageService;
import by.intexsoft.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImageService imageService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ImageService imageService) {
        this.productRepository = productRepository;
        this.imageService = imageService;
    }

    @Override
    public Product create(Product object) {
        return productRepository.save(object);
    }

    @Override
    public Product find(int id) {
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
    public Product update(Product object) {
        return productRepository.save(object);
    }

    @Override
    public void delete(int id) {
        Product productToDelete = productRepository.findOne(id);
        if (isNotEmpty(productToDelete.getImage()))
            imageService.delete(productToDelete.getImage());
        productRepository.delete(productToDelete);
    }
}
