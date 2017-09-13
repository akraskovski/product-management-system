package by.kraskovski.pms.controller.dto;

import by.kraskovski.pms.domain.model.User;
import lombok.Getter;

import java.io.Serializable;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Token Data Transfer Object class
 */
@Getter
public class TokenDto implements Serializable {

    /**
     * {@link User}'s token
     */
    private String token;

    /**
     * Current logged {@link User}
     */
    private User user;

    public TokenDto(final String token, final User user) {
        this.token = token;
        this.user = user;
        this.user.setPassword(EMPTY);
    }
}
