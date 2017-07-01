package by.kraskovski.pms.controller;

import by.kraskovski.pms.domain.User;
import by.kraskovski.pms.domain.dto.TokenDTO;
import by.kraskovski.pms.security.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Handle requests for authentication operations.
 * Works with {@link TokenService}.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(final TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * Login method
     * Find {@link by.kraskovski.pms.domain.User} in database by username
     * Generate token from {@link TokenService}
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody final User requestUser) {
        LOGGER.info("Start authentication user with username: " + requestUser.getUsername());
        try {
            final TokenDTO tokenDTO = tokenService.generate(requestUser.getUsername(), requestUser.getPassword());
            Optional.ofNullable(tokenDTO).orElseThrow(() -> new IllegalArgumentException("Generated token is null."));
                LOGGER.info("User authentication with username: {} successful!", requestUser.getUsername());
                return new ResponseEntity<>(tokenDTO, HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException | BadCredentialsException e) {
            LOGGER.error(
                    "User authentication with username: {} failed! Cause: {}",
                    requestUser.getUsername(),
                    e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
