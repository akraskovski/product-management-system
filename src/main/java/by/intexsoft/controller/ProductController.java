package by.intexsoft.controller;

import by.intexsoft.model.Product;
import by.intexsoft.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping("/")
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
    @RequestMapping("/{name}")
    public List<Product> loadProducts(@PathVariable("name") String name) {
        LOGGER.info("Start loadProduct: " + name);
        try {
            return productService.findByName(name);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in loadProduct. " + e.getLocalizedMessage());
            return null;
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void createProduct(@RequestBody Product product) {
        LOGGER.info("Start createProduct");
        try {
            productService.create(product);
        } catch (Exception e) {
            LOGGER.info("Error in createProduct. " + e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("id") Integer id) {
        LOGGER.info("Start deleteProduct");
        try {
            productService.delete(id);
        } catch (Exception e) {
            LOGGER.info("Error in deleteProduct. " + e.getLocalizedMessage());
        }
    }
}
