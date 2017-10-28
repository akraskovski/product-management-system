package by.kraskovski.pms.application.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
