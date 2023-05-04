package com.pekao.projektpekao.controller.Users;

import com.pekao.projektpekao.domain.User;

import java.util.List;


public class UserEntityMapper {

    private UserEntityMapper(){}

    public static User userEntity(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .buildNewEntity();
    }

    public static List<User> toUserEntityList(List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(UserEntityMapper::userEntity)
                .toList();
    }

}
