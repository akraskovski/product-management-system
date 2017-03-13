package by.intexsoft.security.service;

public interface TokenService {

    String generateToken(String username, String password);
}
