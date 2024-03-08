package com.lppduy.blogblink.service;

import com.lppduy.blogblink.domain.entity.User;

import java.util.Optional;

public interface UserValidationService {
    void validateUsernameAndEmail(String newUsername, String newEmail, Long userId);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
}
