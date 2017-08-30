package by.kraskovski.pms.controller;

import by.kraskovski.pms.domain.User;
import by.kraskovski.pms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Controller for the {@link UserService}.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Return json-information about all users in database.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity loadAllUsers() {
        LOGGER.info("Start loadAllUsers");
        try {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.error("Exception in loadAllUsers. " + e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Return json-information about all users in database.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity loadUserById(@PathVariable("id") final String id) {
        LOGGER.info("Start loadUserById");
        try {
            final User user = userService.find(id);
            Assert.notNull(user, "Unable to find user with id: " + id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find user in database with setting name in browser.
     */
    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    public ResponseEntity loadUserByUsername(@PathVariable final String username) {
        LOGGER.info("Start loadUserByUsername: " + username);
        try {
            final User user = userService.findByUsername(username);
            Assert.notNull(user, "Unable to find user with username: " + username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Creating {@link User} from client form.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody final User user) {
        LOGGER.info("Start createUser: " + user.getUsername());
        try {
            return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.error("Exception in createUser. " + e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update {@link User} entity in database.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody final User user) {
        LOGGER.info("Start updateUser: " + user.getUsername());
        try {
            return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while updating user with id" + user.getId() + ". " + e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete {@link User} from database by identifier.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("id") final String id) {
        LOGGER.info("Start deleteUser with id: " + id);
        try {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while delete user with id: " + id + ". " + e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
