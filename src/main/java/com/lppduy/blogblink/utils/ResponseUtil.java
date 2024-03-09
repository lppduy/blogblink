package com.lppduy.blogblink.utils;

import com.lppduy.blogblink.domain.dto.ApiExceptionResponse;
import com.lppduy.blogblink.domain.dto.ApiResponse;
import com.lppduy.blogblink.enums.ResponseCode;
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
    public static ResponseEntity<ApiExceptionResponse> createResponse(ResponseCode responseCode, List<String> message) {
        ApiExceptionResponse exceptionResponse = new ApiExceptionResponse(
                new Date(),
                responseCode.getHttpStatus().value(),
                responseCode.getHttpStatus(),
                responseCode.getReponseCode(),
                message
        );
        return new ResponseEntity<>(exceptionResponse, responseCode.getHttpStatus());
    }

    public static <T> ResponseEntity<ApiResponse<T>> createResponse(T data, ResponseCode responseCode) {
        ApiResponse<T> apiResponse = new ApiResponse<>(
                new Date(),
                responseCode.getHttpStatus().value(),
                responseCode.getHttpStatus(),
                responseCode.getReponseCode(),
                responseCode.getMessage(),
                data
        );

        return new ResponseEntity<>(apiResponse, responseCode.getHttpStatus());
    }
}
