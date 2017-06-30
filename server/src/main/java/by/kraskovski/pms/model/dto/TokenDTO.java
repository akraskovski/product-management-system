package by.kraskovski.pms.model.dto;

import by.kraskovski.pms.model.User;

import java.io.Serializable;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Token Data Transfer Object class
 */
public class TokenDTO implements Serializable {

    /**
     * {@link User}'s token
     */
    private String token;

    /**
     * Current logged {@link User}
     */
    private User user;

    public TokenDTO(final String token, final User user) {
        this.token = token;
        this.user = user;
        this.user.setPassword(EMPTY);
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
