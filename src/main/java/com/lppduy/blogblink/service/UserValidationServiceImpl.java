package com.lppduy.blogblink.service;

import com.lppduy.blogblink.domain.entity.User;
import com.lppduy.blogblink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class UserValidationServiceImpl implements UserValidationService{
    @Autowired
    UserRepository userRepository;

    public void validateUsernameAndEmail(String newUsername, String newEmail, Long userId) {
        findUserByUsername(newUsername).ifPresent(user -> {
            if (userId == null || !user.getId().equals(userId)) {
                throw new RuntimeException("Username is already taken: " + newUsername);
            }
        });

        findUserByEmail(newEmail).ifPresent(user -> {
            if (userId == null || !user.getId().equals(userId)) {
                throw new RuntimeException("Email is already in use: " + newEmail);
            }
        });
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
