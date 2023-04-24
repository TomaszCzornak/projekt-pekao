package com.pekao.projektpekao.controller.Users;

import com.pekao.projektpekao.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDtoMapper {

    private UserDtoMapper(){}

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static List<UserDto> userDtos(List<User> userList) {
        return userList.stream()
                .map(UserDtoMapper::toUserDto)
                .collect(Collectors.toList());
    }
}
