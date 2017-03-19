package by.intexsoft.controller;

import by.intexsoft.model.Stock;
import by.intexsoft.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @return entites of {@link Stock}
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> loadAllStocks() {
        LOGGER.info("Start loadAllStocks");
        try {
            return new ResponseEntity<>(stockService.findAll(), HttpStatus.OK);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in loadAllStocks. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Creating {@link Stock} from client form
     * @param stock model
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createStock(@RequestBody Stock stock) {
        LOGGER.info("Start createStock");
        try {
            return new ResponseEntity<>(stockService.create(stock), HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.info("Error in createStock. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update {@link Stock} entity in database
     * @param stock model
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStock(@RequestBody Stock stock) {
        LOGGER.info("Start updateStock");
        try {
            return new ResponseEntity<>(stockService.update(stock), HttpStatus.OK);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in updateStock. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete {@link Stock} from database by identifier
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStock(@PathVariable("id") Integer id) {
        LOGGER.info("Start deleteStock");
        try {
            stockService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in deleteStock. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
