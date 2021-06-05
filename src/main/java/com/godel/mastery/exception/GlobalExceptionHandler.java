package com.godel.mastery.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for handling exceptions in specific handler classes and/or handler methods.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handle(ResourceNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handle(ValidationException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Catch exception when fields of dto-entities are not valid.
     *
     * @param ex - MethodArgumentNotValidException
     * @return - exception message with code.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handle(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> errors = result.getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());

        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Catch exception when uri contains incorrect data, which check in controllers.
     *
     * @param ex - ConstraintViolationException
     * @return - exception message with code.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> handle(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .collect(Collectors.toList());

        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Catch exception when uri contains incorrect type of data.
     * For example, tag/7 - find tag by id, expected int, but enter string - tag/ddf.
     *
     * @param ex - MethodArgumentTypeMismatchException
     * @return - exception message with code.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handle(MethodArgumentTypeMismatchException ex) {
        String errorMessage = "Incorrect parameter of request.";
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    /**
     * Catch exception when LocalDate is not valid.
     *
     * @param ex - HttpMessageNotReadableException
     * @return - exception message with code.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handle(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getRootCause().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}