package by.kraskovski.pms.application.controller.dto;

import by.kraskovski.pms.domain.model.Authority;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static by.kraskovski.pms.application.validation.Patterns.PASSWORD_PATTERN;
import static by.kraskovski.pms.application.validation.Patterns.PHONE_PATTERN;

/**
 * Data transfer object for {@link by.kraskovski.pms.domain.model.User}
 */
@Getter
@Setter
public class UserDto {
    private String id;

    @NotNull
    private String username;

    @NotNull
    @Pattern(regexp = PASSWORD_PATTERN)
    private String password;

    private String firstName;
    private String lastName;
    private String email;

    @Pattern(regexp = PHONE_PATTERN)
    private String phone;
    private String avatar;
    private List<Authority> authorities;
    private LocalDateTime createDate;
}
