package by.kraskovski.pms.domain.service.exception;

/**
 * Custom exception if files not found in system
 */
public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(final String message) {
        super(message);
    }
}
