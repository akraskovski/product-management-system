package by.intexsoft.controller;

import by.intexsoft.model.User;
import by.intexsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles requests for the {@link UserService}
 */
@RestController
@RequestMapping("/service")
public class SimpleController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleController.class);

    /**
     * Simple method to print string
     *
     * @return test string
     */
    @RequestMapping("/hello")
    public String sayHello() {
        LOGGER.info("Start hello");
        return "Hello, my name is Artem";
    }

    /**
     * Return json-information about all users in database
     *
     * @return list of {@link User}s
     */
    @RequestMapping("/users")
    public List<User> loadAllUsers() {
        LOGGER.info("Start loadAllUsers");
        try {
            return userService.findAll();
        } catch (NullPointerException e) {
            LOGGER.error("Exception in getAllUsers. " + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Find user in database with setting name in browser
     *
     * @return entity of {@link User}
     */
    @RequestMapping("/users/{username}")
    public User loadUser(@PathVariable String username) {
        LOGGER.info("Start loadUser: " + username);
        try {
            return userService.findByUsername(username);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in getUserByName. " + e.getLocalizedMessage());
            return null;
        }
    }
}
