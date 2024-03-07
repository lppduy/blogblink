package com.lppduy.blogblink.domain.dto;

import lombok.Data;

@Data
public class UserUpdateRequestDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
