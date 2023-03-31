package com.pekao.projektpekao.controller;

import com.pekao.projektpekao.entity.User;
import com.pekao.projektpekao.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.removeUserById(id);
    }

    @PostMapping()
    public User postUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    @PutMapping("/{id}")
    public void putUser(@PathVariable("id") Long id, @RequestBody User user) {
        userService.updateUser(id, user);

    }
}