package com.lppduy.blogblink.exception;

import com.lppduy.blogblink.domain.dto.ApiExceptionResponse;
import com.lppduy.blogblink.utils.ResponseUtil;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApiExceptionHandler implements ErrorController {
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
        return ResponseUtil.createResponse(exception.getErrorCode(), exception.getErrors());
    }

}
