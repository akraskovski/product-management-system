package by.kraskovski.pms.security.exception;

/**
 * Exception handling for {@link by.kraskovski.pms.domain.User}
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(final String message) {
        super(message);
    }
}
