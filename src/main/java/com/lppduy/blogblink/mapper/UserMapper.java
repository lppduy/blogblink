package com.lppduy.blogblink.mapper;

import com.lppduy.blogblink.domain.dto.UserAddRequestDTO;
import com.lppduy.blogblink.domain.dto.UserResponseDTO;
import com.lppduy.blogblink.domain.dto.UserUpdateRequestDTO;
import com.lppduy.blogblink.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "profileImageUrl",ignore = true)
    User userAddRequestDTOToUser(UserAddRequestDTO dto);
    @Mapping(target = "profileImageUrl",ignore = true)
    User userUpdateRequestDTOToUser(UserUpdateRequestDTO dto, @MappingTarget User user);
    UserResponseDTO userToUserResponseDTO(User user);
    List<UserResponseDTO> usersToUserResponseDTOs(List<User> users);
}
