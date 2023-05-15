package com.pekao.projektpekao.controller.Users;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UsersResponse {

    private List<UserDto> usersResponseList;
}
