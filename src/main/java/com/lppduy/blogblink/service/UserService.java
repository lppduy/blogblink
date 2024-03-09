package com.lppduy.blogblink.service;

import com.lppduy.blogblink.domain.dto.*;
import com.lppduy.blogblink.domain.entity.User;
import com.lppduy.blogblink.exception.CustomApiException;
import com.lppduy.blogblink.exception.EmailExistException;
import com.lppduy.blogblink.exception.UsernameExistException;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User createUser(UserAddRequestDTO user) throws IOException, EmailExistException, UsernameExistException, CustomApiException;
    User updateUser(Long userId, UserUpdateRequestDTO user) throws IOException, EmailExistException, UsernameExistException, CustomApiException;
    User removeUser(Long userId) throws CustomApiException;
    PaginationResponseDTO<UserResponseDTO> getAllUsers(UserSearchRequestDTO userSearchRequestDTO);
}
