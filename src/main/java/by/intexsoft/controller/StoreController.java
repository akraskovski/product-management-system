package by.intexsoft.controller;

import by.intexsoft.model.Store;
import by.intexsoft.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleController.class);

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
}
