package by.kraskovski.pms.controller;

import by.kraskovski.pms.domain.model.Store;
import by.kraskovski.pms.service.StoreService;
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
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for the {@link StoreService}.
 */
@RestController
@RequestMapping("/store")
@Slf4j
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(final StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * Find all stores in database.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity loadAllStores() {
        log.info("Start loadAllStores");
        try {
            return new ResponseEntity<>(storeService.findAll(), HttpStatus.OK);
        } catch (DataAccessException e) {
            log.error("Exception in loadAllStores. " + e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find stores in database with setting id in browser.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity loadStoreById(@PathVariable("id") final String id) {
        log.info("Start loadStoreById: " + id);
        try {
            final Store store = storeService.find(id);
            Assert.notNull(store, "Unable to find store with id: " + id);
            return new ResponseEntity<>(store, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Creating {@link Store} from client form.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createStore(@RequestBody final Store store) {
        log.info("Start createStore");
        try {
            return new ResponseEntity<>(storeService.create(store), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            log.error("Exception in createStore. " + e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update {@link Store} entity in database.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateStore(@RequestBody final Store store) {
        log.info("Start updateStore");
        try {
            return new ResponseEntity<>(storeService.update(store), HttpStatus.OK);
        } catch (DataAccessException e) {
            log.error("Exception in updateStore. " + e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete {@link Store} from database by identifier.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteStore(@PathVariable("id") final String id) {
        log.info("Start deleteStore");
        try {
            storeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            log.error("Exception in deleteStore. " + e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
