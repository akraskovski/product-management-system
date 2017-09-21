package by.kraskovski.pms.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Data transfer object for login
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class LoginDto implements Serializable {

    private String username;
    private String password;
}
