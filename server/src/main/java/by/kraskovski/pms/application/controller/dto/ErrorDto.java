package by.kraskovski.pms.application.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data transfer object for response errors
 */
@Getter
@AllArgsConstructor
public class ErrorDto {
    private String message;
}
