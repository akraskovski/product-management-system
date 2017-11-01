package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.Product;
import by.kraskovski.pms.domain.repository.ProductRepository;
import by.kraskovski.pms.domain.service.exception.FileNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductItemService implements ProductService {

    private final ProductRepository productRepository;
    private final FileService imageService;

    @Override
    public Product create(final Product object) {
        if (Objects.isNull(object.getManufactureDate())) {
            object.setManufactureDate(LocalDateTime.now());
        }
        return productRepository.save(object);
    }

    @Override
    public Product find(final String id) {
        return Optional.ofNullable(productRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + " not found in db!"));
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
    public void delete(final String id) {
        final Product productToDelete = find(id);
        if (isNotEmpty(productToDelete.getImage())) {
            try {
                imageService.delete(productToDelete.getImage());
            } catch (FileNotFoundException e) {
                log.warn("Product: {} doesn't have image with id: {}!",
                        productToDelete.getId(), productToDelete.getImage());
            }
        }
        productRepository.delete(productToDelete);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
}
