package com.lppduy.blogblink.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class ApiExceptionResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss", timezone = "UTC")
    private Date timestamp;

    private int statusCode;

    private HttpStatus status;

    private String errorCode;

    private List<String> messages;
}
