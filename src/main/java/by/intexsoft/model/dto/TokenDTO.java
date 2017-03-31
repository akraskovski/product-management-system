package by.intexsoft.model.dto;

import by.intexsoft.model.User;

import java.io.Serializable;

/**
 * Token Data Transfer Object class
 */
public class TokenDTO implements Serializable {

    /**
     * {@link User}'s token
     */
    public String token;

    /**
     * Current logged {@link User}ser
     */
    public User user;

    public TokenDTO(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
