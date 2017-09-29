package by.kraskovski.pms.application.security.service;

import by.kraskovski.pms.application.controller.dto.TokenDto;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Service for generating token
 */
public interface TokenService {

    /**
     * Generate and return token
     */
    TokenDto generate(String username, String password);

    /**
     * Authenticate method
     * Working with security
     */
    Authentication authenticate(HttpServletRequest request);
}
