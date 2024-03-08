package com.lppduy.blogblink.utils;

import com.lppduy.blogblink.domain.dto.ApiExceptionResponse;
import com.lppduy.blogblink.domain.dto.ApiResponse;
import com.lppduy.blogblink.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public class ResponseUtil {
    public static ResponseEntity<ApiExceptionResponse> createResponse(HttpStatus httpStatus, List<String> message) {
        ApiExceptionResponse exceptionResponse = new ApiExceptionResponse(
                new Date(),
                httpStatus.value(),
                httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(),
                message
        );
        return new ResponseEntity<>(exceptionResponse, httpStatus);
    }
    public static ResponseEntity<ApiExceptionResponse> createResponse(ErrorCode errorCode, List<String> message) {
        ApiExceptionResponse exceptionResponse = new ApiExceptionResponse(
                new Date(),
                errorCode.getHttpStatus().value(),
                errorCode.getHttpStatus(),
                errorCode.getErrorCode(),
                message
        );
        return new ResponseEntity<>(exceptionResponse, errorCode.getHttpStatus());
    }

    public static <T> ResponseEntity<ApiResponse<T>> createResponse(T data, ErrorCode errorCode) {
        ApiResponse<T> apiResponse = new ApiResponse<>(
                new Date(),
                errorCode.getHttpStatus().value(),
                errorCode.getHttpStatus(),
                errorCode.getErrorCode(),
                errorCode.getMessage(),
                data
        );

        return new ResponseEntity<>(apiResponse, errorCode.getHttpStatus());
    }
}
