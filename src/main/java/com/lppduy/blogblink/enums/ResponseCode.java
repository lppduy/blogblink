package com.lppduy.blogblink.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    GET_SUCCESS(HttpStatus.OK, "GET_SUCCESS", "Get operation successful"),
    CREATE_SUCCESS(HttpStatus.CREATED, "CREATE_SUCCESS", "Create operation successful"),
    UPDATE_SUCCESS(HttpStatus.OK, "UPDATE_SUCCESS", "Update operation successful"),
    DELETE_SUCCESS(HttpStatus.OK, "DELETE_SUCCESS", "Delete operation successful"),
    USERNAME_ALREADY_TAKEN(HttpStatus.BAD_REQUEST, "USERNAME_ALREADY_TAKEN", "Username is already taken"),
    EMAIL_ALREADY_IN_USE(HttpStatus.BAD_REQUEST, "EMAIL_ALREADY_IN_USE", "Email is already in use"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "User not found");

    private final HttpStatus httpStatus;
    private final String reponseCode;
    private final String message;
}
