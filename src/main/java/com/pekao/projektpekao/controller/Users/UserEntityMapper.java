package com.pekao.projektpekao.controller.Users;

import com.pekao.projektpekao.domain.User.User;
import com.pekao.projektpekao.domain.User.UserParams;

import java.time.LocalDate;
import java.util.List;


public class UserEntityMapper {

    private UserEntityMapper(){}

    public static User toUserEntity(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .createdAt(LocalDate.now())
                .buildNewEntity();
    }

    public static List<User> toUserEntityList(List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(UserEntityMapper::toUserEntity)
                .toList();
    }

    public static User toUserEntity(UserParams userParams) {
        return User.builder()
                .id(userParams.getId())
                .firstName(userParams.getFirstName())
                .lastName(userParams.getLastName())
                .email(userParams.getEmail())
                .createdAt(userParams.getCreatedAt())
                .build();
    }

}
