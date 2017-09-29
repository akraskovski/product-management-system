package by.kraskovski.pms.application.controller.dto;

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
    private UserDto userDto;

    public TokenDto(final String token, final UserDto userDto) {
        this.token = token;
        this.userDto = userDto;
        this.userDto.setPassword(EMPTY);
    }
}
