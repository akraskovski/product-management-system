package by.intexsoft.controller;

import by.intexsoft.model.Stock;
import by.intexsoft.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleController.class);

    @Autowired
    private StockService stockService;

    /**
     * Find all products in database
     * @return entites of {@link Stock}
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Stock> loadAllStocks() {
        LOGGER.info("Start loadAllStocks");
        try {
            return stockService.findAll();
        } catch (NullPointerException e) {
            LOGGER.error("Exception in loadAllStocks. " + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Creating {@link Stock} from client form
     * @param stock model
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void createStock(@RequestBody Stock stock) {
        LOGGER.info("Start createProduct");
        try {
            stockService.create(stock);
        } catch (Exception e) {
            LOGGER.info("Error in createProduct. " + e.getLocalizedMessage());
        }
    }

    /**
     * Update {@link Stock} entity in database
     * @param stock model
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void updateStock(@RequestBody Stock stock) {
        LOGGER.info("Start update Stock");
        try {
            stockService.update(stock.id, stock);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in update Stock. " + e.getLocalizedMessage());
        }
    }
}
