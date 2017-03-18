package by.intexsoft.controller;

import by.intexsoft.model.User;
import by.intexsoft.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles requests for the {@link UserService}
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Return json-information about all users in database
     * @return list of {@link User}s
     */
    @RequestMapping("/")
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
     * @return entity of {@link User}
     */
    @RequestMapping("/{username}")
    public User loadUser(@PathVariable String username) {
        LOGGER.info("Start loadUser: " + username);
        try {
            return userService.findByUsername(username);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in getUserByName. " + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Creating {@link User} from client form
     * @param user model
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        LOGGER.info("Start createUser");
        try {
            userService.create(user);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in createUser. " + e.getLocalizedMessage());
        }
    }

    /**
     * Update {@link User} entity in database
     * @param user model
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user) {
        LOGGER.info("Start updateUser");
        try {
            userService.update(user);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in updateUser. " + e.getLocalizedMessage());
        }
    }

    /**
     * Delete {@link User} from database by identifier
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Integer id) {
        LOGGER.info("Start deleteUser");
        try {
            userService.delete(id);
        } catch (NullPointerException e) {
            LOGGER.error("Exception in deleteUser. " + e.getLocalizedMessage());
        }
    }
}
