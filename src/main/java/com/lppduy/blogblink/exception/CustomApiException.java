package com.lppduy.blogblink.exception;

import com.lppduy.blogblink.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class CustomApiException extends Exception{
    private final ErrorCode errorCode;
    private final List<String> errors;

    public CustomApiException(ErrorCode  errorCode, String... messages) {
        super(String.join(", ", messages));
        this.errorCode = errorCode;
        this.errors = Arrays.asList(messages);
    }
}
