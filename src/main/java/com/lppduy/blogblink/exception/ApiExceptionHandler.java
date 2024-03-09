package com.lppduy.blogblink.exception;

import com.lppduy.blogblink.domain.dto.ApiExceptionResponse;
import com.lppduy.blogblink.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ApiExceptionHandler implements ErrorController {

    @Value("${app.environment}")
    private String environment;

    private static final String ERROR_PROCESSING_FILE = "Failed to handle the file";
    private static final String METHOD_IS_NOT_ALLOWED = "Method %s is not allowed";
    private static final String INTERNAL_SERVER_ERROR_MSG = "Server faced an unexpected issue";
    private static final String NO_MAPPING_MSG = "No route found for the given URL";
    private static final String ERROR_PATH = "/error";
    @ExceptionHandler({UsernameExistException.class})
    public ResponseEntity<ApiExceptionResponse> usernameExistException(UsernameExistException exception) {
        return ResponseUtil.createResponse(BAD_REQUEST, Collections.singletonList(exception.getMessage()));
    }

    @ExceptionHandler({EmailExistException.class})
    public ResponseEntity<ApiExceptionResponse> emailExistException(EmailExistException exception) {
        return ResponseUtil.createResponse(BAD_REQUEST, Collections.singletonList(exception.getMessage()));
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<ApiExceptionResponse> handleCustomApiException(CustomApiException exception) {
        return ResponseUtil.createResponse(exception.getResponseCode(), exception.getErrors());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMap.put(fieldName, errorMessage);
        });

        errorMap.forEach((field, error) -> {
            errors.add(field + ": " + error);
        });

        return ResponseUtil.createResponse(HttpStatus.BAD_REQUEST, errors);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handleNoHandlerFoundException(NoHandlerFoundException e) {
        return ResponseUtil.createResponse(NOT_FOUND, Collections.singletonList(NO_MAPPING_MSG));
    }

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<ApiExceptionResponse> handleNotFoundError() {
        return ResponseUtil.createResponse(NOT_FOUND, Collections.singletonList(NO_MAPPING_MSG));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiExceptionResponse> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        String errorMsg = String.format(METHOD_IS_NOT_ALLOWED, supportedMethod);
        return ResponseUtil.createResponse(METHOD_NOT_ALLOWED, Collections.singletonList(errorMsg));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiExceptionResponse> handleIOException(IOException exception) {
        return ResponseUtil.createResponse(INTERNAL_SERVER_ERROR, Collections.singletonList(ERROR_PROCESSING_FILE));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> handleInternalServerErrorException(Exception exception) {
        String mainMessage = exception.getMessage();
        String causeMessage;

        if ("dev".equals(environment)) {
            causeMessage = (exception.getCause() != null) ? exception.getCause().getMessage() : INTERNAL_SERVER_ERROR_MSG;
        } else {
            causeMessage = INTERNAL_SERVER_ERROR_MSG;
        }

        List<String> errorMessages = Arrays.asList(mainMessage, causeMessage);
        return ResponseUtil.createResponse(INTERNAL_SERVER_ERROR, errorMessages);
    }


}
