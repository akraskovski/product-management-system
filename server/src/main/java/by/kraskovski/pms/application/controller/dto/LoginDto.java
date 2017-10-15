package by.kraskovski.pms.application.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Data transfer object for login
 */
@Getter
@AllArgsConstructor
public class LoginDto implements Serializable {
    private String username;
    private String password;
}
