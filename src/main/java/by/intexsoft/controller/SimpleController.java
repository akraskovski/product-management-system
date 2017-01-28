package by.intexsoft.controller;

import by.intexsoft.model.User;
import by.intexsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles requests for the {@link User} service.
 */
@RestController
@RequestMapping(value = "/service")
public class SimpleController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(SimpleController.class);

    /**
     * Simple method to print string.
     * @return test string.
     */
    @RequestMapping(value = "/hello")
    public String hello() {
        logger.info("Start hello");
        return "Hello, my name is Artem";
    }

    /**
     * Return json-information about all users in database.
     * @return list of users.
     */
    @RequestMapping(value = "/users")
    public List<User> getAllUsers() {
        try {
            logger.info("Start getAllUsers");
            return userService.getAllUsers();
        } catch (NullPointerException e) {
            logger.error("Exception in getAllUsers. " + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Find user in database with setting name in browser.
     * @param name - to search.
     * @return entity of user.
     */
    @RequestMapping(value = "/users/{name}")
    public User getUserByName(@PathVariable String name) {
        try {
            logger.info("Start getUserByName: " + name);
            return userService.findUserByName(name);
        } catch (NullPointerException e) {
            logger.error("Exception in getUserByName. " + e.getLocalizedMessage());
            return null;
        }
    }
}
