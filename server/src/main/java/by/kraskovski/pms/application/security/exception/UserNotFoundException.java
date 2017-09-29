package by.kraskovski.pms.application.security.exception;

import by.kraskovski.pms.domain.model.User;

/**
 * Exception handling for {@link User}
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(final String message) {
        super(message);
    }
}
