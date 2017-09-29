package by.kraskovski.pms.domain.service.exception;

/**
 * Custom exception for {@link org.springframework.web.multipart.MultipartFile} uploading error
 */
public class FileUploadException extends RuntimeException {

    public FileUploadException(final String message) {
        super(message);
    }
}
