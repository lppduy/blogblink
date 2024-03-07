package com.lppduy.blogblink.service;

import com.lppduy.blogblink.domain.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User updateUser(Long userId, User user);
    User removeUser(Long userId);
    List<User> getAllUsers();
}
