package by.kraskovski.pms.domain.dto;

import by.kraskovski.pms.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Token Data Transfer Object class
 */
@Getter
public class TokenDTO implements Serializable {

    /**
     * {@link User}'s token
     */
    private String token;

    /**
     * Current logged {@link User}
     */
    private User user;

    public TokenDTO(String token, User user) {
        this.token = token;
        this.user = user;
        this.user.setPassword(EMPTY);
    }
}
