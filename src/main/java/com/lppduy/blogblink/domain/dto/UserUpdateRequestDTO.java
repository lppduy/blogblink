package com.lppduy.blogblink.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserUpdateRequestDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private MultipartFile profileImageUrl;
}
