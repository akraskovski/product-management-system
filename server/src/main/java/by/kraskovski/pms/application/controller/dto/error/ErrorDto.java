package by.kraskovski.pms.application.controller.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for response errors
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    private ErrorTypeDto type;
    private String message;

    /**
     * Creates new error dto with server type.
     *
     * @param message error content
     * @return error dto
     */
    public static ErrorDto createServerErrorDto(final String message) {
        final ErrorDto result = new ErrorDto();

        result.setType(ErrorTypeDto.SERVER);
        result.setMessage(message);

        return result;
    }
}
