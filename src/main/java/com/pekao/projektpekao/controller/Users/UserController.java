package com.pekao.projektpekao.controller.Users;

import com.pekao.projektpekao.domain.User.User;
import com.pekao.projektpekao.domain.User.UserParams;
import com.pekao.projektpekao.domain.User.UserParamsMapper;
import com.pekao.projektpekao.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public UsersResponse getAllUsers() {
        List<UserDto> userDtoList = UserDtoMapper.toUserDtoList(userService.findAllUsers());
        return UsersResponse.builder()
                .usersResponseList(userDtoList)
                .build();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable("id") Long id) {
        UserDto singleUser = UserDtoMapper.toUserDto(userService.findUserById(id));
        return UserResponse.builder()
                .userResponse(singleUser)
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.removeUserById(id);
    }

    @PostMapping()
    public UserResponse postUser(@RequestBody UserDto userDto) {
        UserParams userParams = UserParamsMapper.toUserParams(userDto);
        User userSaved = userService.addUser(userParams);
        UserDto userDtoMapped = UserDtoMapper.toUserDto(userSaved);
        return UserResponse.builder()
                .userResponse(userDtoMapped)
                .build();
    }
    @PutMapping("/{id}")
    public void putUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        if(Objects.equals(id, userDto.getId())) {
            throw new IllegalStateException("Id in path and in body are not the same");
        }
        UserParams userParams = UserParamsMapper.toUserParams(userDto);
        userService.updateUser(userParams);

    }
}
