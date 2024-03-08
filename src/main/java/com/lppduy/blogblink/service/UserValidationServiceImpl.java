package com.lppduy.blogblink.service;

import com.lppduy.blogblink.domain.entity.User;
import com.lppduy.blogblink.enums.ErrorCode;
import com.lppduy.blogblink.exception.CustomApiException;
import com.lppduy.blogblink.exception.EmailExistException;
import com.lppduy.blogblink.exception.UsernameExistException;
import com.lppduy.blogblink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class UserValidationServiceImpl implements UserValidationService{
    @Autowired
    UserRepository userRepository;

    public void validateUsernameAndEmail(String newUsername, String newEmail, Long userId) throws CustomApiException {
        Optional<User> userWithUsername = findUserByUsername(newUsername);
        if (userWithUsername.isPresent()) {
            User user = userWithUsername.get();
            if (userId == null || !user.getId().equals(userId)) {
                throw new CustomApiException(ErrorCode.USERNAME_ALREADY_TAKEN, ErrorCode.USERNAME_ALREADY_TAKEN.getMessage() +": " + newUsername);
            }
        }

        Optional<User> userWithEmail = findUserByEmail(newEmail);
        if (userWithEmail.isPresent()) {
            User user = userWithEmail.get();
            if (userId == null || !user.getId().equals(userId)) {
                throw new CustomApiException(ErrorCode.EMAIL_ALREADY_IN_USE, ErrorCode.EMAIL_ALREADY_IN_USE.getMessage() +": "+ newEmail);
            }
        }
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
