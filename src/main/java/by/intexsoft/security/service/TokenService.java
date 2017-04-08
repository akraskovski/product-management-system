package by.intexsoft.security.service;

import by.intexsoft.model.User;

/**
 * Service for generating token
 */
public interface TokenService {

    /**
     * Generate and return token
     */
    String generate(User user, String password);
}
