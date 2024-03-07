package com.lppduy.blogblink.controller;

import com.lppduy.blogblink.domain.dto.UserAddRequestDTO;
import com.lppduy.blogblink.domain.dto.UserResponseDTO;
import com.lppduy.blogblink.domain.dto.UserUpdateRequestDTO;
import com.lppduy.blogblink.domain.entity.User;
import com.lppduy.blogblink.mapper.UserMapper;
import com.lppduy.blogblink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/","/user"})
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody UserAddRequestDTO userAddRequestDTO) {
        User user = userMapper.userAddRequestDTOToUser(userAddRequestDTO)
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("update/{userId}")
    public ResponseEntity<User> updateUser(
           @PathVariable Long userId, @ RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        User user = userMapper.userUpdateRequestDTOToUser(userUpdateRequestDTO);
        User updatedUser = userService.updateUser(userId,user);
        if (updatedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        User updatedUser = userService.removeUser(userId);
        if (updatedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        List<UserResponseDTO> userResponseDTOList = userMapper.usersToUserResponseDTOs(userList);
        return new ResponseEntity<>(userResponseDTOList, HttpStatus.OK);
    }

}


