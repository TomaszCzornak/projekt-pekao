package com.pekao.projektpekao.controller;

import com.pekao.projektpekao.entity.User;


public class UserEntityMapper {

    private UserEntityMapper(){}

    public static User userEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .commentList(CommentEntityMapper.toCommentListEntity(userDto.getCommentDtoList()))
                .build();
    }

}
