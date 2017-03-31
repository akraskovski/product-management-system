package by.intexsoft.controller;

import by.intexsoft.model.Store;
import by.intexsoft.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for the {@link StoreService}
 */
@RestController
@RequestMapping("/store")
public class StoreController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreController.class);

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * Find all stores in database
     *
     * @return entites of {@link Store}
     */
    @RequestMapping
    public ResponseEntity<?> loadAllStores() {
        LOGGER.info("Start loadAllStores");
        try {
            return new ResponseEntity<>(storeService.findAll(), HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.error("Exception in loadAllStores. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find stores in database with setting id in browser
     *
     * @return entity of {@link Store}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> loadStoreById(@PathVariable("id") Integer id) {
        LOGGER.info("Start loadStoreById: " + id);
        try {
            Store store = storeService.find(id);
            Assert.notNull(store, "Unable to find store with id: " + id);
            return new ResponseEntity<>(store, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Creating {@link Store} from client form
     *
     * @param store model
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createStore(@RequestBody Store store) {
        LOGGER.info("Start createStore");
        try {
            return new ResponseEntity<>(storeService.create(store), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.error("Exception in createStore. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update {@link Store} entity in database
     *
     * @param store model
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateStore(@RequestBody Store store) {
        LOGGER.info("Start updateStore");
        try {
            return new ResponseEntity<>(storeService.update(store), HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.error("Exception in updateStore. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete {@link Store} from database by identifier
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStore(@PathVariable("id") Integer id) {
        LOGGER.info("Start deleteStore");
        try {
            storeService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.error("Exception in deleteStore. " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
