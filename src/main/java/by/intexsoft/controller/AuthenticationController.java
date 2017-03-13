package by.intexsoft.controller;

import by.intexsoft.dto.LoginDTO;
import by.intexsoft.dto.TokenDTO;
import by.intexsoft.security.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handle requests for authentication operations
 * Works with {@link TokenService}
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleController.class);

    @Autowired
    private TokenService tokenService;

    /**
     * Login method
     * Parse income {@link LoginDTO} object
     * Find {@link by.intexsoft.model.User} in database by username
     * Generate token from {@link TokenService}
     * @return {@link TokenDTO} model
     */
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody LoginDTO dto) {
        LOGGER.info("Start authentication");
        String token = tokenService.generateToken(dto.username, dto.password);
        if (token != null) {
            TokenDTO response = new TokenDTO();
            response.token = token;
            LOGGER.info("Authentication successful! Returning token");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            LOGGER.error("Authentication failed");
            return new ResponseEntity<>("Authentication failed", HttpStatus.BAD_REQUEST);
        }
    }
}