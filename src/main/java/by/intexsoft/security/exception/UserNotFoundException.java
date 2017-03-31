package by.intexsoft.security.exception;

/**
 * Exception handling for {@link by.intexsoft.model.User}
 */
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
