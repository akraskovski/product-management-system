package by.kraskovski.pms.controller;

import by.kraskovski.pms.controller.dto.ProductStockDto;
import by.kraskovski.pms.controller.dto.StockDto;
import by.kraskovski.pms.domain.model.Stock;
import by.kraskovski.pms.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Controller for the {@link StockService}.
 */
@RestController
@RequestMapping("/stock")
@Slf4j
public class StockController {

    private final StockService stockService;
    private final Mapper mapper;

    @Autowired
    public StockController(final StockService stockService, final Mapper mapper) {
        this.stockService = stockService;
        this.mapper = mapper;
    }

    /**
     * Find all products in database.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity loadAllStocks() {
        log.info("Start loadAllStocks");
        return ResponseEntity.ok(stockService.findAll().stream()
                .map(stock -> mapper.map(stock, StockDto.class))
                .collect(toList()));
    }

    /**
     * Find stock in database with setting id in browser.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity loadStockById(@PathVariable("id") final String id) {
        log.info("Start loadStockById: {}", id);
        return ResponseEntity.ok(mapper.map(stockService.find(id), StockDto.class));
    }

    /**
     * Find stock products in database with setting id in browser.
     */
    @RequestMapping(value = "/{id}/products", method = RequestMethod.GET)
    public ResponseEntity loadStockProductsById(@PathVariable("id") final String id) {
        log.info("Start loadStockProductsById: {}", id);
        final List<ProductStockDto> result = stockService.findProducts(id).stream()
                .map(productStock -> mapper.map(productStock, ProductStockDto.class))
                .collect(toList());
        return ResponseEntity.ok(result);
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
        stockService.addProduct(stockId, productId, count);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
        stockService.deleteProduct(stockId, productId, count);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Creating {@link Stock} from client form.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createStock(@RequestBody final StockDto stockDto) {
        log.info("Start createStock: {}", stockDto.getSpecialize());
        final Stock stock = mapper.map(stockDto, Stock.class);
        return new ResponseEntity<>(mapper.map(stockService.create(stock), StockDto.class), HttpStatus.CREATED);
    }

    /**
     * Update {@link Stock} entity in database.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateStock(@RequestBody final StockDto stockDto) {
        log.info("Start updateStock: {}", stockDto.getId());
        final Stock stock = stockService.update(mapper.map(stockDto, Stock.class));
        return ResponseEntity.ok(mapper.map(stock, StockDto.class));
    }

    /**
     * Delete {@link Stock} from database by identifier.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteStock(@PathVariable("id") final String id) {
        log.info("Start deleteStock: {}", id);
        stockService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
