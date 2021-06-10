package com.godel.mastery.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import static com.godel.mastery.constant.ExceptionMessage.DATE_FORMAT_EXCEPTION_MESSAGE;
import static com.godel.mastery.constant.ExceptionMessage.INCORRECT_PARAM_OF_REQUEST_EXCEPTION_MESSAGE;

/**
 * Class for handling exceptions in specific handler classes and/or handler methods.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handle(EmployeeNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public String handle(DateTimeParseException ex) {
        log.error(ex.getMessage(), ex);
        return DATE_FORMAT_EXCEPTION_MESSAGE;
    }

    /**
     * Catch exception when fields of dto-entities are not valid.
     *
     * @param ex - MethodArgumentNotValidException
     * @return - exception message with code.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handle(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> errors = result.getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());

        log.error(ex.getMessage(), ex);
        return errors;
    }

    /**
     * Catch exception when uri contains incorrect data, which check in controllers.
     *
     * @param ex - ConstraintViolationException
     * @return - exception message with code.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<String> handle(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .collect(Collectors.toList());

        log.error(ex.getMessage(), ex);
        return errors;
    }

    /**
     * Catch exception when uri contains incorrect type of data.
     * For example, tag/7 - find tag by id, expected int, but enter string - tag/ddf.
     *
     * @param ex - MethodArgumentTypeMismatchException
     * @return - exception message with code.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handle(MethodArgumentTypeMismatchException ex) {
        log.error(ex.getMessage(), ex);
        return INCORRECT_PARAM_OF_REQUEST_EXCEPTION_MESSAGE;
    }

    /**
     * Catch exception when LocalDate is not valid.
     *
     * @param ex - HttpMessageNotReadableException
     * @return - exception message with code.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handle(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage(), ex);
        return ex.getRootCause().getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handle(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ex.getLocalizedMessage();
    }
}