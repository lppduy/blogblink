package com.lppduy.blogblink.exception;

import com.lppduy.blogblink.enums.ResponseCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class CustomApiException extends Exception{
    private final ResponseCode responseCode;
    private final List<String> errors;

    public CustomApiException(ResponseCode responseCode, String... messages) {
        super(String.join(", ", messages));
        this.responseCode = responseCode;
        this.errors = Arrays.asList(messages);
    }
}
