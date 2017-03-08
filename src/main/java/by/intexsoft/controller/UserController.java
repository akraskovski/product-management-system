package by.intexsoft.controller;

import by.intexsoft.model.User;
import by.intexsoft.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Handles requests for the {@link UserService}
 */
@RestController
@RequestMapping("/service")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleController.class);

    /**
     * Return json-information about all users in database
     *
     * @return list of {@link User}s
     */
    @RequestMapping("/users")
    public List<User> loadAllUsers() {
        LOGGER.info("Start loadAllUsers");
        try {
            List<User> users = userService.findAll();
            return users;
        } catch (NullPointerException e) {
            LOGGER.error("Exception in loadAllUsers. " + e.getLocalizedMessage());
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
