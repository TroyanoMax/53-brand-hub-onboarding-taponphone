package com.am53.visadirect.fundstransactions.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import javax.validation.ConstraintViolationException;
import java.net.MalformedURLException;
import java.util.List;

@RestControllerAdvice
public class GlobalControllerAdvice {

    private static final String CHARACTERS_FORMAT = "%s: %s";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleError(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiError> handleError(DataAccessException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Error: DataAccessException.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Error: DataIntegrityViolationException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ApiError> handleError(InternalServerErrorException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }
    
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleError(BadRequestException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(value = {
            BindException.class,
            ConversionFailedException.class,
            ConstraintViolationException.class,
            MissingPathVariableException.class,
            InvalidDataAccessApiUsageException.class,
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class,
            InvalidMediaTypeException.class})
    public ResponseEntity<ApiError> handleBadRequest(HttpServletRequest request, Exception e) {
        if(e instanceof MethodArgumentNotValidException) {
            return handleMethodArgumentNotValid((MethodArgumentNotValidException) e, request);
        } else if(e instanceof BindException) {
            return handleMethodArgumentNotValid((BindException) e, request);
        } else if(e instanceof ConstraintViolationException) {
            return handleMethodArgumentNotValid((ConstraintViolationException) e, request);
        }
        return defaultHandleException(HttpStatus.BAD_REQUEST, e, request);
    }

    @ExceptionHandler(MalformedURLException.class)
    public ResponseEntity<ApiError> handleInternalError(HttpServletRequest request, Exception e) {
        return defaultHandleException(HttpStatus.INTERNAL_SERVER_ERROR, e, request);
    }

    private ResponseEntity<ApiError> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .flatMap(field -> e.getBindingResult().getFieldErrors(field).stream().map( error ->
                        String.format(CHARACTERS_FORMAT, field, error.getDefaultMessage())
                )).toList();
        return new ResponseEntity<>(
                new ApiError(
                        HttpStatus.BAD_REQUEST,
                        e.getMessage(),
                        request.getServletPath(),
                        errors
                ), new HttpHeaders(), HttpStatus.BAD_REQUEST
        );
    }

    private ResponseEntity<ApiError> handleMethodArgumentNotValid(
            BindException e,
            HttpServletRequest request) {
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .flatMap(field -> e.getBindingResult().getFieldErrors(field).stream().map( error ->
                        String.format(CHARACTERS_FORMAT, field, error.getDefaultMessage())
                )).toList();
        return new ResponseEntity<>(
                new ApiError(
                        HttpStatus.BAD_REQUEST,
                        e.getMessage(),
                        request.getServletPath(),
                        errors
                ), new HttpHeaders(), HttpStatus.BAD_REQUEST
        );
    }

    private ResponseEntity<ApiError> handleMethodArgumentNotValid(
            ConstraintViolationException e,
            HttpServletRequest request) {
        List<String> errors = e.getConstraintViolations()
                .stream()
                .map(error ->
                        String.format(CHARACTERS_FORMAT, error.getPropertyPath().toString(), error.getMessage())
                ).toList();

        return new ResponseEntity<>(
                new ApiError(
                        HttpStatus.BAD_REQUEST,
                        e.getMessage(),
                        request.getServletPath(),
                        errors
                ), new HttpHeaders(), HttpStatus.BAD_REQUEST
        );
    }

    private ResponseEntity<ApiError> defaultHandleException(HttpStatus status, Exception e, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ApiError(
                        status,
                        e.getMessage(),
                        request.getServletPath()
                ), new HttpHeaders(), status
        );
    }

}
