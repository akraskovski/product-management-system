package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.application.controller.dto.ProductStockDto;
import by.kraskovski.pms.application.controller.dto.StockDto;
import by.kraskovski.pms.domain.model.Stock;
import by.kraskovski.pms.domain.service.stock.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static java.util.stream.Collectors.toList;

/**
 * Controller for the {@link StockService}.
 */
@RestController
@RequestMapping("/stock")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StockController {

    private final StockService stockService;
    private final Mapper mapper;

    /**
     * Find all products in database.
     */
    @GetMapping
    public ResponseEntity loadAllStocks() {
        log.info("Start loadAllStocks");
        return ResponseEntity.ok(stockService.findAll().stream()
                .map(stock -> mapper.map(stock, StockDto.class))
                .collect(toList()));
    }

    /**
     * Find stock in database with setting id in browser.
     */
    @GetMapping("/{id}")
    public ResponseEntity loadStockById(@PathVariable final String id) {
        log.info("Start loadStockById: {}", id);
        return ResponseEntity.ok(mapper.map(stockService.find(id), StockDto.class));
    }

    /**
     * Find stock products in database with setting id in browser.
     */
    @GetMapping("/{id}/products")
    public ResponseEntity loadStockProductsById(@PathVariable final String id) {
        log.info("Start loadStockProductsById: {}", id);
        return ResponseEntity.ok(stockService.findProducts(id).stream()
                .map(productStock -> mapper.map(productStock, ProductStockDto.class))
                .collect(toList()));
    }

    /**
     * Add product to the stock
     */
    @PutMapping("/product")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addProductToStock(
            @RequestParam final String stockId,
            @RequestParam final String productId,
            @RequestParam(value = "count", defaultValue = "1", required = false) final int count) {
        log.info("Start add Product: {} from Stock: {} with count: {}", productId, stockId, count);
        stockService.addProduct(stockId, productId, count);
    }

    /**
     * Delete product from the stock
     */
    @DeleteMapping("/product")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductFromStock(
            @RequestParam final String stockId,
            @RequestParam final String productId,
            @RequestParam(value = "count", required = false, defaultValue = "1") final int count) {
        log.info("Start delete Product: {} from Stock: {} with count: {}", productId, stockId, count);
        stockService.deleteProduct(stockId, productId, count);
    }

    /**
     * Creating {@link Stock} from client form.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockDto createStock(@RequestBody @Valid final StockDto stockDto) {
        log.info("Start createStock: {}", stockDto.getSpecialize());
        final Stock createdStock = stockService.create(mapper.map(stockDto, Stock.class), stockDto.getManagerId());
        return mapper.map(createdStock, StockDto.class);
    }

    /**
     * Update {@link Stock} entity in database.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public StockDto updateStock(@RequestBody @Valid final StockDto stockDto) {
        log.info("Start updateStock: {}", stockDto.getId());
        final Stock updatedStock = stockService.update(mapper.map(stockDto, Stock.class), stockDto.getManagerId());
        return mapper.map(updatedStock, StockDto.class);
    }

    /**
     * Delete {@link Stock} from database by identifier.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStock(@PathVariable final String id) {
        log.info("Start deleteStock: {}", id);
        stockService.delete(id);
    }
}
