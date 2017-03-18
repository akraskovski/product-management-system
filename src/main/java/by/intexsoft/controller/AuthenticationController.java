package by.intexsoft.controller;

import by.intexsoft.model.dto.TokenDTO;
import by.intexsoft.model.User;
import by.intexsoft.security.service.TokenService;
import by.intexsoft.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isEmpty;
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
     *
     * @return {@link TokenDTO} model
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody User requestUser) {
        LOGGER.info("Start authentication");
        if (isNotEmpty(requestUser.username) && isNotEmpty(requestUser.password)) {
            User user = userService.findByUsername(requestUser.username);
            String token = tokenService.generate(user, requestUser.password);
            if (token != null) {
                LOGGER.info("Authentication successful! Returning token");
                user.password = EMPTY;
                return new ResponseEntity<>(new TokenDTO(token, user), HttpStatus.OK);
            }
        }
        LOGGER.error("Authentication failed");
        return new ResponseEntity<>("Authentication failed", HttpStatus.BAD_REQUEST);
    }
}