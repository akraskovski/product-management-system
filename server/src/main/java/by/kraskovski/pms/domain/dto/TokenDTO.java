package by.kraskovski.pms.domain.dto;

import by.kraskovski.pms.domain.User;
import lombok.Getter;

import java.io.Serializable;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Token Data Transfer Object class
 */
public class TokenDTO implements Serializable {

    /**
     * {@link User}'s token
     */
    @Getter
    private String token;

    /**
     * Current logged {@link User}
     */
    @Getter
    private User user;

    public TokenDTO(final String token, final User user) {
        this.token = token;
        this.user = user;
        this.user.setPassword(EMPTY);
    }
}
