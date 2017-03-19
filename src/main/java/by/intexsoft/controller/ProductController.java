package by.intexsoft.controller;

import by.intexsoft.model.Product;
import by.intexsoft.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Find all products in database
     * @return entites of {@link Product}
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Product> loadAllProducts() {
        LOGGER.info("Start loadAllProducts");
        try {
            return productService.findAll();
        } catch (NullPointerException e) {
            LOGGER.error("Exception in loadAllProducts. " + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Find products in database with setting name in browser
     * @return entity of {@link Product}
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public List<Product> loadProducts(@PathVariable("name") String name) {
        LOGGER.info("Start loadProduct: " + name);
        try {
            return productService.findByName(name);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in loadProduct. " + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Creating {@link Product} from client form
     * @param product model
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void createProduct(@RequestBody Product product) {
        LOGGER.info("Start createProduct");
        try {
            productService.create(product);
        } catch (Exception e) {
            LOGGER.info("Error in createProduct. " + e.getLocalizedMessage());
        }
    }

    /**
     * Update {@link Product}'s information in database
     * @param product model
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void updateProduct(@RequestBody Product product) {
        LOGGER.info("start updateProduct");
        try {
            productService.update(product);
        } catch (Exception e) {
            LOGGER.info("Error in updateProduct. " + e.getLocalizedMessage());
        }
    }

    /**
     * Delete {@link Product} from database by identifier
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
        LOGGER.info("Start deleteProduct");
        try {
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("Error in deleteProduct. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
