package by.kraskovski.pms.controller;

import by.kraskovski.pms.model.Stock;
import by.kraskovski.pms.service.StockService;
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

/**
 * Controller for the {@link StockService}.
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);
    private final StockService stockService;

    @Autowired
    public StockController(final StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Find all products in database.
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
     * Find stocks in database with setting id in browser.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity loadStockById(@PathVariable("id") final int id) {
        LOGGER.info("Start loadStockById: " + id);
        try {
            final Stock stock = stockService.find(id);
            Assert.notNull(stock, "Unable to find stock with id: " + id);
            return new ResponseEntity<>(stock, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Creating {@link Stock} from client form.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createStock(@RequestBody final Stock stock) {
        LOGGER.info("Start createStock");
        try {
            return new ResponseEntity<>(stockService.create(stock), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info("Error in createStock. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update {@link Stock} entity in database.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateStock(@RequestBody final Stock stock) {
        LOGGER.info("Start updateStock");
        try {
            return new ResponseEntity<>(stockService.update(stock), HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.error("Exception in updateStock. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete {@link Stock} from database by identifier.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteStock(@PathVariable("id") final int id) {
        LOGGER.info("Start deleteStock");
        try {
            stockService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            LOGGER.error("Exception in deleteStock. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
