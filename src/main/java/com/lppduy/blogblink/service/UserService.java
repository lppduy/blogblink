package com.lppduy.blogblink.service;

import com.lppduy.blogblink.domain.dto.*;
import com.lppduy.blogblink.domain.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User createUser(UserAddRequestDTO user) throws IOException;
    User updateUser(Long userId, UserUpdateRequestDTO user) throws IOException;
    User removeUser(Long userId);
    PaginationResponseDTO<UserResponseDTO> getAllUsers(UserSearchRequestDTO userSearchRequestDTO);
}
