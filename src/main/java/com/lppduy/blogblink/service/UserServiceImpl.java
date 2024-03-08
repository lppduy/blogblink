package com.lppduy.blogblink.service;

import com.lppduy.blogblink.domain.dto.UserAddRequestDTO;
import com.lppduy.blogblink.domain.dto.UserUpdateRequestDTO;
import com.lppduy.blogblink.domain.entity.User;
import com.lppduy.blogblink.mapper.UserMapper;
import com.lppduy.blogblink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ImageService imageService;

    @Override
    public User createUser(UserAddRequestDTO userAddRequestDTO) throws IOException {
        String imageName = imageService.saveProfileImage(null, userAddRequestDTO.getUsername(), userAddRequestDTO.getProfileImageUrl());
        User userEntity = userMapper.userAddRequestDTOToUser(userAddRequestDTO);
        userEntity.setProfileImageUrl(imageName);
        return userRepository.save(userEntity);
    }

    @Override
    public User updateUser(Long userId, UserUpdateRequestDTO userUpdateRequestDTO) throws IOException {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found with id: " + userId));

        userMapper.userUpdateRequestDTOToUser(userUpdateRequestDTO, existingUser);

        String imageName = imageService.saveProfileImage(existingUser.getProfileImageUrl(),userUpdateRequestDTO.getUsername(),userUpdateRequestDTO.getProfileImageUrl());
        existingUser.setProfileImageUrl(imageName);

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
