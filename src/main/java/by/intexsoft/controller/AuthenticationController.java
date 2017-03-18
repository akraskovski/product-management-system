package by.intexsoft.controller;

import by.intexsoft.model.dto.TokenDTO;
import by.intexsoft.model.User;
import by.intexsoft.security.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handle requests for authentication operations
 * Works with {@link TokenService}
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * Login method
     * Find {@link by.intexsoft.model.User} in database by username
     * Generate token from {@link TokenService}
     *
     * @return {@link TokenDTO} model
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody User user) {
        LOGGER.info("Start authentication");
        String token = tokenService.generate(user.username, user.password);
        if (token != null) {
            LOGGER.info("Authentication successful! Returning token");
            return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
        }
        LOGGER.error("Authentication failed");
        return new ResponseEntity<>("Authentication failed", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private void securedMethod() {

    }
}