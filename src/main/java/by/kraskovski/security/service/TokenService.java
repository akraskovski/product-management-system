package by.kraskovski.security.service;

import by.kraskovski.model.User;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Service for generating token
 */
public interface TokenService {

    /**
     * Generate and return token
     */
    String generate(User user, String password);

    /**
     * Authenticate method
     * Working with security
     */
    Authentication authenticate(HttpServletRequest request);
}
