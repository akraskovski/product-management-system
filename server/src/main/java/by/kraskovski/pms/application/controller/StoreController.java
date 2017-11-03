package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.application.controller.dto.StockDto;
import by.kraskovski.pms.application.controller.dto.StoreDto;
import by.kraskovski.pms.domain.model.Store;
import by.kraskovski.pms.domain.service.StoreService;
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
 * Controller for the {@link StoreService}.
 */
@RestController
@RequestMapping("/store")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StoreController {

    private final StoreService storeService;
    private final Mapper mapper;

    /**
     * Find all stores in database
     */
    @GetMapping
    public ResponseEntity loadAllStores() {
        log.info("Start loadAllStores");
        return ResponseEntity.ok(storeService.findAll().stream()
                .map(store -> mapper.map(store, StoreDto.class))
                .collect(toList()));
    }

    /**
     * Find stores in database with setting id in browser
     */
    @GetMapping("/{id}")
    public ResponseEntity loadStoreById(@PathVariable final String id) {
        log.info("Start loadStoreById: {}", id);
        return ResponseEntity.ok(mapper.map(storeService.find(id), StoreDto.class));
    }

    /**
     * Find stocks related to store
     */
    @GetMapping("/{id}/stock-manage")
    public ResponseEntity loadStoreStocksById(@PathVariable final String id) {
        log.info("Start loadStoreStocksById: {}", id);
        return ResponseEntity.ok(storeService.find(id).getStockList().stream()
                .map(stock -> mapper.map(stock, StockDto.class)).collect(toList()));
    }

    /**
     * Creating {@link Store} from client form.
     */
    @PostMapping
    public ResponseEntity createStore(@RequestBody @Valid final StoreDto storeDto) {
        log.info("Start createStore: {}", storeDto.getName());
        final Store store = mapper.map(storeDto, Store.class);
        return new ResponseEntity<>(mapper.map(storeService.create(store), StoreDto.class), HttpStatus.CREATED);
    }

    /**
     * Create relation between {@link by.kraskovski.pms.domain.model.Stock} and {@link Store}
     */
    @PutMapping("/stock-manage")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addStock(@RequestParam final String storeId,
                         @RequestParam final String stockId) {
        log.info("Start add stock: {} to store: {}", stockId, storeId);
        storeService.addStock(storeId, stockId);
    }

    /**
     * Delete relation between {@link by.kraskovski.pms.domain.model.Stock} and {@link Store}
     */
    @DeleteMapping("/stock-manage")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStock(@RequestParam final String storeId,
                            @RequestParam final String stockId) {
        log.info("Start delete stock: {} from store: {}", stockId, storeId);
        storeService.deleteStock(storeId, stockId);
    }

    /**
     * Update {@link Store} entity in database.
     */
    @PutMapping
    public ResponseEntity updateStore(@RequestBody @Valid final StoreDto storeDto) {
        log.info("Start updateStore: {}", storeDto.getName());
        final Store store = mapper.map(storeDto, Store.class);
        return ResponseEntity.ok(mapper.map(storeService.update(store), StoreDto.class));
    }

    /**
     * Delete {@link Store} from database by identifier.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStore(@PathVariable final String id) {
        log.info("Start deleteStore: {}", id);
        storeService.delete(id);
    }
}
