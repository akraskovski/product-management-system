package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.application.controller.dto.UserDto;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.domain.model.enums.AuthorityEnum;
import by.kraskovski.pms.domain.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Controller for the {@link UserService}.
 */
@RestController
@RequestMapping("/user")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;
    private final Mapper mapper;

    /**
     * Return json-information about all users in database.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> loadAllUsers() {
        log.info("Start loadAllUsers");
        return userService.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(toList());
    }

    /**
     * Return json-information about all users in database.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto loadUserById(@PathVariable final String id) {
        log.info("Start loadUserById: {}", id);
        return mapper.map(userService.find(id), UserDto.class);
    }

    /**
     * Find user in database with setting name.
     */
    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto loadUserByUsername(@PathVariable final String username) {
        log.info("Start loadUserByUsername: {}", username);
        return mapper.map(userService.findByUsername(username), UserDto.class);
    }

    /**
     * Find user in database with setting role.
     */
    @GetMapping("/role/{role}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> loadUsersByRole(@PathVariable final AuthorityEnum role) {
        log.info("Start loadUsersByRole: {}", role);
        return userService.findByRole(role).stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(toList());
    }

    /**
     * Creating {@link User} from client form.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid final UserDto userDto) {
        log.info("Start createUser: {}", userDto.getUsername());
        final User user = mapper.map(userDto, User.class);
        return mapper.map(userService.create(user), UserDto.class);
    }

    /**
     * Update {@link User} entity in database.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@RequestBody @Valid final UserDto userDto) {
        log.info("Start updateUser: {}", userDto.getUsername());
        final User user = mapper.map(userDto, User.class);
        return mapper.map(userService.update(user), UserDto.class);
    }

    /**
     * Delete {@link User} from database by identifier.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable final String id) {
        log.info("Start deleteUser with id: {}", id);
        userService.delete(id);
    }

    /**
     * Load current user from context.
     */
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getCurrentUser() {
        return mapper.map(userService.getCurrentUser(), UserDto.class);
    }
}
