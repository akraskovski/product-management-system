package by.intexsoft.security.service;

import by.intexsoft.model.User;

/**
 * Service for generating token
 */
public interface TokenService {

    /**
     * Generate token
     * @return generated token
     */
    String generate(User user, String password);
}
