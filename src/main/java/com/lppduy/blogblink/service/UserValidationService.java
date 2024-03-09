package com.lppduy.blogblink.service;

import com.lppduy.blogblink.domain.entity.User;
import com.lppduy.blogblink.exception.CustomApiException;
import com.lppduy.blogblink.exception.EmailExistException;
import com.lppduy.blogblink.exception.UsernameExistException;

import java.util.Optional;

public interface UserValidationService {
    void validateUsernameAndEmail(String newUsername, String newEmail, Long userId) throws UsernameExistException, EmailExistException, CustomApiException;
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
}
