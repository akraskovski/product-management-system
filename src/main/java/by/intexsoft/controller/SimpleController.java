package by.intexsoft.controller;

import by.intexsoft.model.Product;
import by.intexsoft.model.Stock;
import by.intexsoft.model.Store;
import by.intexsoft.model.User;
import by.intexsoft.service.ProductService;
import by.intexsoft.service.StockService;
import by.intexsoft.service.StoreService;
import by.intexsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles requests for the {@link UserService}
 */
@RestController
@RequestMapping("/service")
public class SimpleController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private StockService stockService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleController.class);

    /**
     * Simple method to print string
     *
     * @return test string
     */
    @RequestMapping("/hello")
    public String sayHello() {
        LOGGER.info("Start hello");
        return "Hello, my name is Artem";
    }

    @RequestMapping("/products")
    public List<Product> loadAllProducts() {
        LOGGER.info("Start loadAllProducts");
        try {
            List<Product> productList = productService.findAll();
            return productList;
        } catch (NullPointerException e) {
            LOGGER.error("Exception in loadAllProducts. " + e.getLocalizedMessage());
            return null;
        }
    }

    @RequestMapping("/stores")
    public List<Store> loadAllStores() {
        LOGGER.info("Start loadAllStores");
        try {
            List<Store> storeList = storeService.findAll();
            return storeList;
        } catch (NullPointerException e) {
            LOGGER.error("Exception in loadAllStores. " + e.getLocalizedMessage());
            return null;
        }
    }

    @RequestMapping("/stocks")
    public List<Stock> loadAllStocks() {
        LOGGER.info("Start loadAllStocks");
        try {
            List<Stock> stockList = stockService.findAll();
            return stockList;
        } catch (NullPointerException e) {
            LOGGER.error("Exception in loadAllStocks. " + e.getLocalizedMessage());
            return null;
        }
    }
}
