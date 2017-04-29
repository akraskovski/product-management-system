package by.intexsoft.controller;

import by.intexsoft.model.User;
import by.intexsoft.model.dto.TokenDTO;
import by.intexsoft.service.TokenService;
import by.intexsoft.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Handle requests for authentication operations
 * Works with {@link TokenService}
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public AuthenticationController(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    /**
     * Login method
     * Find {@link by.intexsoft.model.User} in database by username
     * Generate token from {@link TokenService}
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity authenticate(@RequestBody User requestUser) {
        LOGGER.info("Start authentication user with username: " + requestUser.getUsername());
        if (isNotEmpty(requestUser.getUsername()) && isNotEmpty(requestUser.getPassword())) {
            User user = userService.findByUsername(requestUser.getUsername());
            String token = tokenService.generate(user, requestUser.getPassword());
            if (token != null) {
                LOGGER.info("Authentication successful! user with username: " + requestUser.getUsername());
                user.setPassword(EMPTY);
                return new ResponseEntity<>(new TokenDTO(token, user), HttpStatus.OK);
            }
        }
        LOGGER.error("Authentication failed user with username: " + requestUser.getUsername());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}