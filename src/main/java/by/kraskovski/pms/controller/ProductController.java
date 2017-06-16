package by.kraskovski.pms.controller;

import by.kraskovski.pms.model.Product;
import by.kraskovski.pms.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Controller for the {@link ProductService}.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    /**
     * Find all products in database.
     */
    @RequestMapping(method = RequestMethod.GET)
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
     * Find products in database with setting name in browser.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity loadProductById(@PathVariable("id") final int id) {
        LOGGER.info("Start loadProductById: " + id);
        try {
            final Product product = productService.find(id);
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
    public ResponseEntity loadProductsByName(@PathVariable("name") final String name) {
        LOGGER.info("Start loadProductsByName: " + name);
        try {
            final List<Product> product = productService.findByName(name);
            Assert.notNull(product, "Unable to find products with name: " + name);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find products in database with setting type in browser.
     */
    @RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
    public ResponseEntity loadProductsByType(@PathVariable("type") final String type) {
        LOGGER.info("Start loadProductsByType: " + type);
        try {
            final List<Product> product = productService.findByType(type);
            Assert.notNull(product, "Unable to find products with type: " + type);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Creating {@link Product} from client form.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createProduct(@RequestBody final Product product) {
        LOGGER.info("Start createProduct");
        try {
            return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info("Error in createProduct. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update {@link Product}'s information in database.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateProduct(@RequestBody final Product product) {
        LOGGER.info("start updateProduct");
        try {
            return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info("Error in updateProduct. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete {@link Product} from database by identifier.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable("id") final int id) {
        LOGGER.info("Start deleteProduct");
        try {
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            LOGGER.info("Error in deleteProduct. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
