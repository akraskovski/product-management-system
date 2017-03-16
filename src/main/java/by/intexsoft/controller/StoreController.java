package by.intexsoft.controller;

import by.intexsoft.model.Store;
import by.intexsoft.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreController.class);

    @Autowired
    private StoreService storeService;

    /**
     * Find all stores in database
     * @return entites of {@link Store}
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Store> loadAllStores() {
        LOGGER.info("Start loadAllStores");
        try {
            return storeService.findAll();
        } catch (NullPointerException e) {
            LOGGER.error("Exception in loadAllStores. " + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Creating {@link Store} from client form
     * @param store model
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void createStore(@RequestBody Store store) {
        LOGGER.info("Start createStore");
        try {
            storeService.create(store);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in createStore. " + e.getLocalizedMessage());
        }
    }

    /**
     * Update {@link Store} entity in database
     * @param store model
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void updateStore(@RequestBody Store store) {
        LOGGER.info("Start updateStore");
        try {
            storeService.update(store.id, store);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in updateStore. " + e.getLocalizedMessage());
        }
    }

    /**
     * Delete {@link Store} from database by identifier
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStore(@PathVariable("id") Integer id) {
        LOGGER.info("Start deleteStore");
        try {
            storeService.delete(id);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in deleteStore. " + e.getLocalizedMessage());
        }
    }
}
