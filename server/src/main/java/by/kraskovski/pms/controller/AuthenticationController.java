package by.kraskovski.pms.controller;

import by.kraskovski.pms.controller.dto.LoginDto;
import by.kraskovski.pms.controller.dto.TokenDto;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.security.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handle requests for authentication operations.
 * Works with {@link TokenService}.
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(final TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * Login method
     * Find {@link User} in database by username
     * Generate token from {@link TokenService}
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody final LoginDto loginDto) {
        log.info("Start authentication user with username: " + loginDto.getUsername());
        final TokenDto tokenDto = tokenService.generate(loginDto.getUsername(), loginDto.getPassword());
        return ResponseEntity.ok(tokenDto);
    }
}
