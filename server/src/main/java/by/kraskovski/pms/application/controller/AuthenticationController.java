package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.application.controller.dto.LoginDto;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.application.security.service.TokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Handle requests for authentication operations.
 * Works with {@link TokenService}.
 */
@RestController
@RequestMapping("/auth")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private final TokenService tokenService;

    /**
     * Login method
     * Find {@link User} in database by username
     * Generate token from {@link TokenService}
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody @Valid final LoginDto loginDto) {
        log.info("Start authentication user with username: " + loginDto.getUsername());
        return ResponseEntity.ok(tokenService.generate(loginDto.getUsername(), loginDto.getPassword()));
    }
}
