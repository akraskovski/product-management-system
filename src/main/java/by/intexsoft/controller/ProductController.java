package by.intexsoft.controller;

import by.intexsoft.model.Product;
import by.intexsoft.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the {@link ProductService}
 */
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
     */
    @RequestMapping
    public ResponseEntity loadAllProducts() {
        LOGGER.info("Start loadAllProducts");
        try {
            return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.error("Exception in loadAllProducts. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find products in database with setting name in browser
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity loadProductById(@PathVariable("id") int id) {
        LOGGER.info("Start loadProductById: " + id);
        try {
            Product product = productService.find(id);
            Assert.notNull(product, "Unable to find product with id: " + id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find products in database with setting name in browser
     */
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity loadProductsByName(@PathVariable("name") String name) {
        LOGGER.info("Start loadProductByName: " + name);
        try {
            List<Product> product = productService.findByName(name);
            Assert.notNull(product, "Unable to find product with name: " + name);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Creating {@link Product} from client form
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createProduct(@RequestBody Product product) {
        LOGGER.info("Start createProduct");
        try {
            return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info("Error in createProduct. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update {@link Product}'s information in database
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateProduct(@RequestBody Product product) {
        LOGGER.info("start updateProduct");
        try {
            return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info("Error in updateProduct. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete {@link Product} from database by identifier
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable("id") int id) {
        LOGGER.info("Start deleteProduct");
        try {
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info("Error in deleteProduct. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
