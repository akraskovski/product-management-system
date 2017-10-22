package by.kraskovski.pms.application.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static by.kraskovski.pms.application.validation.Patterns.PASSWORD_PATTERN;

/**
 * Data transfer object for login
 */
@Getter
@AllArgsConstructor
public class LoginDto implements Serializable {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
