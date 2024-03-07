package com.lppduy.blogblink.domain.dto;

import lombok.Data;

@Data
public class UserAddRequestDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
