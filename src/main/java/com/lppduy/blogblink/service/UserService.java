package com.lppduy.blogblink.service;

import com.lppduy.blogblink.domain.dto.UserAddRequestDTO;
import com.lppduy.blogblink.domain.dto.UserUpdateRequestDTO;
import com.lppduy.blogblink.domain.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User createUser(UserAddRequestDTO user) throws IOException;
    User updateUser(Long userId, UserUpdateRequestDTO user);
    User removeUser(Long userId);
    List<User> getAllUsers();
}
