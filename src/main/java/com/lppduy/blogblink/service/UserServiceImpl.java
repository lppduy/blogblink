package com.lppduy.blogblink.service;

import com.lppduy.blogblink.domain.entity.User;
import com.lppduy.blogblink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {

        Optional<User> existingUserOpt = userRepository.findById(userId);
        User existingUser = existingUserOpt.get();

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getUsername());

        return userRepository.save(existingUser);
    }

    @Override
    public User removeUser(Long userId) {
        Optional<User> existingUserOpt = userRepository.findById(userId);
        userRepository.delete(existingUserOpt.get());
        return existingUserOpt.get();
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}
