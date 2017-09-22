package by.kraskovski.pms.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer object for response errors
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {

    private String message;
}
