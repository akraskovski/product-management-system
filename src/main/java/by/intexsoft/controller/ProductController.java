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
    public ResponseEntity<?> loadAllProducts() {
        LOGGER.info("Start loadAllProducts");
        try {
            return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in loadAllProducts. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find products in database with setting name in browser
     * @return entity of {@link Product}
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> loadProducts(@PathVariable("name") String name) {
        LOGGER.info("Start loadProduct: " + name);
        try {
            return new ResponseEntity<>(productService.findByName(name), HttpStatus.OK);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in loadProduct. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Creating {@link Product} from client form
     * @param product model
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        LOGGER.info("Start createProduct");
        try {
            return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.info("Error in createProduct. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update {@link Product}'s information in database
     * @param product model
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        LOGGER.info("start updateProduct");
        try {
            return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("Error in updateProduct. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
