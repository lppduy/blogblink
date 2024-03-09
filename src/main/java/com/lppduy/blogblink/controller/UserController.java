package com.lppduy.blogblink.controller;

import com.lppduy.blogblink.domain.dto.*;
import com.lppduy.blogblink.domain.entity.User;
import com.lppduy.blogblink.enums.ResponseCode;
import com.lppduy.blogblink.exception.ApiExceptionHandler;
import com.lppduy.blogblink.exception.CustomApiException;
import com.lppduy.blogblink.exception.EmailExistException;
import com.lppduy.blogblink.exception.UsernameExistException;
import com.lppduy.blogblink.service.UserService;
import com.lppduy.blogblink.utils.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping(path = {"/", "/user"})
public class UserController extends ApiExceptionHandler {

    @Autowired
    UserService userService;


    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<User>> addUser(@Valid UserAddRequestDTO userAddRequestDTO) throws IOException, CustomApiException, EmailExistException, UsernameExistException {
        User newUser = userService.createUser(userAddRequestDTO);
        return ResponseUtil.createResponse(newUser, ResponseCode.CREATE_SUCCESS);
    }

    @PutMapping(value = "/update/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long userId, @Valid UserUpdateRequestDTO userUpdateRequestDTO) throws IOException, CustomApiException, EmailExistException, UsernameExistException {
        User updatedUser = userService.updateUser(userId, userUpdateRequestDTO);
        return ResponseUtil.createResponse(updatedUser, ResponseCode.UPDATE_SUCCESS);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse<User>> deleteUser(@PathVariable Long userId) throws CustomApiException {
        User deletedUser = userService.removeUser(userId);
        return ResponseUtil.createResponse(deletedUser, ResponseCode.DELETE_SUCCESS);
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<PaginationResponseDTO<UserResponseDTO>>> getAllUsers(@ModelAttribute UserSearchRequestDTO userSearchRequestDTO) {
        userSearchRequestDTO.updatePageable();
        PaginationResponseDTO<UserResponseDTO> userList = userService.getAllUsers(userSearchRequestDTO);
        return ResponseUtil.createResponse(userList, ResponseCode.GET_SUCCESS);
    }
}
