package com.lppduy.blogblink.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class UserResponseDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private String profileImageUrl;
}

