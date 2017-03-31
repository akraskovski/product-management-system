package by.intexsoft.security.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for authentication token
 */
public interface TokenAuthenticationService {

    /**
     * Authenticate method
     * Working with security
     * @param request
     * @return user authentication
     */
    Authentication authenticate(HttpServletRequest request);
}
