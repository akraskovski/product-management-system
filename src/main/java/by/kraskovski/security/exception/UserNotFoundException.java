package by.kraskovski.security.exception;

/**
 * Exception handling for {@link by.kraskovski.model.User}
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(final String message) {
        super(message);
    }
}
