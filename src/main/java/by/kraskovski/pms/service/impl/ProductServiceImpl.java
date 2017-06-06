package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.model.Product;
import by.kraskovski.pms.repository.ProductRepository;
import by.kraskovski.pms.service.ImageService;
import by.kraskovski.pms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImageService imageService;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository, final ImageService imageService) {
        this.productRepository = productRepository;
        this.imageService = imageService;
    }

    @Override
    public Product create(final Product object) {
        return productRepository.save(object);
    }

    @Override
    public Product find(final int id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> findByName(final String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findByType(final String type) {
        return productRepository.findByType(type);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(final Product object) {
        return productRepository.save(object);
    }

    @Override
    public void delete(final int id) {
        final Product productToDelete = productRepository.findOne(id);
        if (isNotEmpty(productToDelete.getImage())) {
            imageService.delete(productToDelete.getImage());
        }
        productRepository.delete(productToDelete);
    }
}
