package com.lppduy.blogblink.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String saveProfileImage(String imageNameOld, String imageNameNew, MultipartFile profileImage) throws IOException;
    void deleteUserProfileImage(String profileImageUrl);
}
