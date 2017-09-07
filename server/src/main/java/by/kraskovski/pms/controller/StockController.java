package by.kraskovski.pms.controller;

import by.kraskovski.pms.domain.model.Stock;
import by.kraskovski.pms.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for the {@link StockService}.
 */
@RestController
@RequestMapping("/stock")
@Slf4j
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(final StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Find all products in database.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity loadAllStocks() {
        log.info("Start loadAllStocks");
        try {
            return new ResponseEntity<>(stockService.findAll(), HttpStatus.OK);
        } catch (DataAccessException e) {
            log.error("Exception in loadAllStocks. " + e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find stock in database with setting id in browser.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity loadStockById(@PathVariable("id") final String id) {
        log.info("Start loadStockById: " + id);
        try {
            final Stock stock = stockService.find(id);
            Assert.notNull(stock, "Unable to find stock with id: " + id);
            return new ResponseEntity<>(stock, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find stock products in database with setting id in browser.
     */
    @RequestMapping(value = "/{id}/products", method = RequestMethod.GET)
    public ResponseEntity loadStockProductsById(@PathVariable("id") final String id) {
        log.info("Start loadStockProductsById: " + id);
        try {
            return new ResponseEntity<>(stockService.findProducts(id), HttpStatus.OK);
        } catch (DataAccessException e) {
            log.error(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Add product to the stock
     */
    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    public ResponseEntity addProductToStock(
            @RequestParam("stock_id") final String stockId,
            @RequestParam("product_id") final String productId,
            @RequestParam(value = "count", defaultValue = "1", required = false) final int count) {
        log.info("Start add Product: {} from Stock: {} with count: {}", productId, stockId, count);
        return stockService.addProduct(stockId, productId, count)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Delete product from the stock
     */
    @RequestMapping(value = "/product", method = RequestMethod.DELETE)
    public ResponseEntity deleteProductFromStock(
            @RequestParam("stock_id") final String stockId,
            @RequestParam("product_id") final String productId,
            @RequestParam(value = "count", required = false, defaultValue = "1") final int count) {
        log.info("Start delete Product: {} from Stock: {} with count: {}", productId, stockId, count);
        return stockService.deleteProduct(stockId, productId, count)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Creating {@link Stock} from client form.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createStock(@RequestBody final Stock stock) {
        log.info("Start createStock");
        try {
            return new ResponseEntity<>(stockService.create(stock), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            log.info("Error in createStock. " + e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update {@link Stock} entity in database.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateStock(@RequestBody final Stock stock) {
        log.info("Start updateStock");
        try {
            return new ResponseEntity<>(stockService.update(stock), HttpStatus.OK);
        } catch (DataAccessException e) {
            log.error("Exception in updateStock. " + e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete {@link Stock} from database by identifier.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteStock(@PathVariable("id") final String id) {
        log.info("Start deleteStock");
        try {
            stockService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            log.error("Exception in deleteStock. " + e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
