package com.lppduy.blogblink.service;

import com.lppduy.blogblink.domain.dto.*;
import com.lppduy.blogblink.domain.entity.User;
import com.lppduy.blogblink.enums.ResponseCode;
import com.lppduy.blogblink.exception.CustomApiException;
import com.lppduy.blogblink.exception.EmailExistException;
import com.lppduy.blogblink.exception.UsernameExistException;
import com.lppduy.blogblink.mapper.UserMapper;
import com.lppduy.blogblink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ImageService imageService;

    @Autowired
    UserValidationService userValidationService;

    @Override
    public User createUser(UserAddRequestDTO userAddRequestDTO) throws IOException, CustomApiException, EmailExistException, UsernameExistException {
        userValidationService.validateUsernameAndEmail(userAddRequestDTO.getUsername(), userAddRequestDTO.getEmail(), null);
        String imageName = imageService.saveProfileImage(null, userAddRequestDTO.getUsername(), userAddRequestDTO.getProfileImageUrl());
        User userEntity = userMapper.userAddRequestDTOToUser(userAddRequestDTO);
        userEntity.setProfileImageUrl(imageName);
        return userRepository.save(userEntity);
    }

    @Override
    public User updateUser(Long userId, UserUpdateRequestDTO userUpdateRequestDTO) throws IOException, CustomApiException, EmailExistException, UsernameExistException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException(ResponseCode.USER_NOT_FOUND, ResponseCode.USER_NOT_FOUND.getMessage() + ": " + userId));

        userMapper.userUpdateRequestDTOToUser(userUpdateRequestDTO, existingUser);
        userValidationService.validateUsernameAndEmail(userUpdateRequestDTO.getUsername(), userUpdateRequestDTO.getEmail(), userId);
        String imageName = imageService.saveProfileImage(existingUser.getProfileImageUrl(), userUpdateRequestDTO.getUsername(), userUpdateRequestDTO.getProfileImageUrl());
        existingUser.setProfileImageUrl(imageName);
        return userRepository.save(existingUser);

    }

    @Override
    public User removeUser(Long userId) throws CustomApiException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException(ResponseCode.USER_NOT_FOUND, ResponseCode.USER_NOT_FOUND.getMessage() + ": " + userId));

        imageService.deleteUserProfileImage(existingUser.getProfileImageUrl());
        userRepository.delete(existingUser);
        return existingUser;
    }

    @Override
    public PaginationResponseDTO<UserResponseDTO> getAllUsers(UserSearchRequestDTO userSearchRequestDTO) {
        Page<User> usersPage = userRepository.findAllUsers(
                userSearchRequestDTO.getStartDate(),
                userSearchRequestDTO.getEndDate(),
                userSearchRequestDTO.getSearchTerm(),
                userSearchRequestDTO.getPageable()
        );
        List<UserResponseDTO> userResponseDTOs = userMapper.usersToUserResponseDTOs(usersPage);
        return new PaginationResponseDTO<>(
                userResponseDTOs,
                usersPage.getTotalElements(),
                usersPage.getNumber() + 1,
                usersPage.getSize()
        );
    }
}

