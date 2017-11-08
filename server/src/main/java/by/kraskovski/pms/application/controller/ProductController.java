package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.application.controller.dto.ProductDto;
import by.kraskovski.pms.domain.model.Product;
import by.kraskovski.pms.domain.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Controller for the {@link ProductService}.
 */
@RestController
@RequestMapping("/product")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;
    private final Mapper mapper;

    /**
     * Find all products in database.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> loadAllProducts() {
        log.info("Start loadAllProducts");
        return productService.findAll().stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .collect(toList());
    }

    /**
     * Find products in database with setting id in browser.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto loadProductById(@PathVariable final String id) {
        log.info("Start loadProductById: {}", id);
        return mapper.map(productService.find(id), ProductDto.class);
    }

    /**
     * Find products in database with setting name in browser
     */
    @GetMapping("/name/{name}")
    public List<ProductDto> loadProductsByName(@PathVariable final String name) {
        log.info("Start loadProductsByName: {}", name);
        return productService.findByName(name).stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .collect(toList());
    }

    /**
     * Find products in database with setting type in browser.
     */
    @GetMapping("/type/{type}")
    public List<ProductDto> loadProductsByType(@PathVariable final String type) {
        log.info("Start loadProductsByType: {}", type);
        return productService.findByType(type).stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .collect(toList());
    }

    /**
     * Creating {@link Product} from client form.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody @Valid final ProductDto productDto) {
        log.info("Start createProduct: {}", productDto.getName());
        final Product product = productService.create(mapper.map(productDto, Product.class));
        return mapper.map(product, ProductDto.class);
    }

    /**
     * Update {@link Product}'s information in database.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateProduct(@RequestBody @Valid final ProductDto productDto) {
        log.info("start updateProduct: {}", productDto.getId());
        final Product product = productService.update(mapper.map(productDto, Product.class));
        return mapper.map(product, ProductDto.class);
    }

    /**
     * Delete {@link Product} from database by identifier.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable final String id) {
        log.info("Start deleteProduct: {}", id);
        productService.delete(id);
    }
}
