package by.kraskovski.pms.application.controller.exception;

import by.kraskovski.pms.application.controller.dto.error.ErrorDto;
import by.kraskovski.pms.application.security.exception.UserNotFoundException;
import by.kraskovski.pms.domain.service.exception.FileNotFoundException;
import by.kraskovski.pms.domain.service.exception.FileUploadException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

/**
 * General exception handler
 */
@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    /**
     * Handle Errors during authentication
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleBadCredentialsException(final BadCredentialsException e) {
        log.warn("User authentication failed! Cause: {}", e.getMessage());
        return new ResponseEntity<>(ErrorDto.createServerErrorDto(e.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handle database exceptions
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity handleDataAccessException(final DataAccessException e) {
        log.warn("Database exception: {}", e.getMessage());
        return new ResponseEntity<>(ErrorDto.createServerErrorDto(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle illegal argument exception
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(final IllegalArgumentException e) {
        log.warn("Illegal argument: {}", e.getMessage());
        return new ResponseEntity<>(ErrorDto.createServerErrorDto(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle FileUploadException while uploading images
     */
    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity handleFileUploadException(final FileUploadException e) {
        log.warn("Error during uploading file: {}", e.getMessage());
        return new ResponseEntity<>(ErrorDto.createServerErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle file not found exception
     */
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity handleFileNotFoundException(final FileNotFoundException e) {
        log.warn("FileNotFound exception: {}", e.getMessage());
        return new ResponseEntity<>(ErrorDto.createServerErrorDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * Handle user not found exception
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(final UserNotFoundException e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>(ErrorDto.createServerErrorDto(e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * Handle entity not found exception
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(final EntityNotFoundException e) {
        log.warn("Resource not found: {}", e.getLocalizedMessage());
        return new ResponseEntity<>(ErrorDto.createServerErrorDto(e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * Handle validation exceptions.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(final ConstraintViolationException e) {
        final StringBuilder builder = new StringBuilder();
        e.getConstraintViolations().forEach(violation -> builder.append(
                String.format("%s Current value: %s", violation.getMessage(), violation.getInvalidValue())));
        log.warn(builder.toString());
        return ResponseEntity.badRequest().body(ErrorDto.createServerErrorDto(builder.toString()));
    }

    /**
     * Handle expired token exception.
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity handleExpiredJwtException(final ExpiredJwtException e) {
        log.warn(e.getLocalizedMessage());
        return new ResponseEntity<>(ErrorDto.createServerErrorDto(e.getLocalizedMessage()), HttpStatus.FORBIDDEN);
    }
}
