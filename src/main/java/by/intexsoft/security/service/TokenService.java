package by.intexsoft.security.service;

/**
 * Service for generating token
 */
public interface TokenService {

    /**
     * Generate token
     * @param username {@link by.intexsoft.model.User#username}
     * @param password {@link by.intexsoft.model.User#username}
     * @return generated token
     */
    String generate(String username, String password);
}
