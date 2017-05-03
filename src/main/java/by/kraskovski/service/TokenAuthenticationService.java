package by.kraskovski.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for authentication token
 */
public interface TokenAuthenticationService {

    /**
     * Authenticate method
     * Working with security
     */
    Authentication authenticate(HttpServletRequest request);
}
