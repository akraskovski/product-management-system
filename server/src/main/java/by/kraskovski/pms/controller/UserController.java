package by.kraskovski.pms.controller;

import by.kraskovski.pms.controller.dto.UserDto;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;

/**
 * Controller for the {@link UserService}.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final Mapper mapper;

    @Autowired
    public UserController(final UserService userService, final Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    /**
     * Return json-information about all users in database.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity loadAllUsers() {
        log.info("Start loadAllUsers");
        return ResponseEntity.ok(userService.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(toList()));
    }

    /**
     * Return json-information about all users in database.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity loadUserById(@PathVariable("id") final String id) {
        log.info("Start loadUserById: {}", id);
        return ResponseEntity.ok(mapper.map(userService.find(id), UserDto.class));
    }

    /**
     * Find user in database with setting name in browser.
     */
    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    public ResponseEntity loadUserByUsername(@PathVariable final String username) {
        log.info("Start loadUserByUsername: {}", username);
        return ResponseEntity.ok(mapper.map(userService.findByUsername(username), UserDto.class));
    }

    /**
     * Creating {@link User} from client form.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody final UserDto userDto) {
        log.info("Start createUser: {}", userDto.getUsername());
        final User user = mapper.map(userDto, User.class);
        return new ResponseEntity<>(mapper.map(userService.create(user), UserDto.class), HttpStatus.CREATED);
    }

    /**
     * Update {@link User} entity in database.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody final UserDto userDto) {
        log.info("Start updateUser: {}", userDto.getUsername());
        final User user = mapper.map(userDto, User.class);
        return ResponseEntity.ok(mapper.map(userService.update(user), UserDto.class));
    }

    /**
     * Delete {@link User} from database by identifier.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") final String id) {
        log.info("Start deleteUser with id: {}", id);
        userService.delete(id);
    }
}
