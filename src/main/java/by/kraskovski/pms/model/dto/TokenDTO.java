package by.kraskovski.pms.model.dto;

import by.kraskovski.pms.model.User;

import java.io.Serializable;

/**
 * Token Data Transfer Object class
 */
public class TokenDTO implements Serializable {

    /**
     * {@link User}'s token
     */
    private final String token;

    /**
     * Current logged {@link User}
     */
    private final User user;

    public TokenDTO(final String token, final User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
