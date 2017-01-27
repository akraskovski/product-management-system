package by.intexsoft.controller;

import by.intexsoft.model.User;
import by.intexsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles requests for the {@link User} service.
 */
@RestController
@RequestMapping(value = "/service")
public class SimpleController {

    @Autowired
    private UserService userService;

    /**
     * Simple method.
     * @return the test string.
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello, my name is Artem";
    }

    /**
     * Return json-information about all users in database.
     * @return list of users.
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Find user in database with setting name in browser.
     * @param name - to search.
     * @return entity of user.
     */
    @RequestMapping(value = "/users/{name}", method = RequestMethod.GET)
    public User getUserByName(@PathVariable String name) {
        try {
            return userService.findUserByName(name);
        } catch (NullPointerException e) {
            return null;
        }
    }
}
