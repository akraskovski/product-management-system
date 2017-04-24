package by.intexsoft.controller;

import by.intexsoft.model.Stock;
import by.intexsoft.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for the {@link StockService}
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);
    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Find all products in database
     */
    @RequestMapping
    public ResponseEntity loadAllStocks() {
        LOGGER.info("Start loadAllStocks");
        try {
            return new ResponseEntity<>(stockService.findAll(), HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.error("Exception in loadAllStocks. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find stocks in database with setting id in browser
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity loadStockById(@PathVariable("id") int id) {
        LOGGER.info("Start loadStockById: " + id);
        try {
            Stock stock = stockService.find(id);
            Assert.notNull(stock, "Unable to find stock with id: " + id);
            return new ResponseEntity<>(stockService.find(id), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Creating {@link Stock} from client form
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createStock(@RequestBody Stock stock) {
        LOGGER.info("Start createStock");
        try {
            return new ResponseEntity<>(stockService.create(stock), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info("Error in createStock. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update {@link Stock} entity in database
     *
     * @param stock model
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateStock(@RequestBody Stock stock) {
        LOGGER.info("Start updateStock");
        try {
            return new ResponseEntity<>(stockService.update(stock), HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.error("Exception in updateStock. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete {@link Stock} from database by identifier
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteStock(@PathVariable("id") int id) {
        LOGGER.info("Start deleteStock");
        try {
            stockService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.error("Exception in deleteStock. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
