package com.pekao.projektpekao.controller.Users;

import com.pekao.projektpekao.domain.User.User;

import java.time.LocalDate;
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
                .createdAt(LocalDate.now())
                .build();
    }

    public static List<UserDto> toUserDtoList(List<User> userList) {
        return userList.stream()
                .map(UserDtoMapper::toUserDto)
                .collect(Collectors.toList());
    }
}
