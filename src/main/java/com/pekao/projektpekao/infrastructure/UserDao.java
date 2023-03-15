package com.pekao.projektpekao.infrastructure;

import com.pekao.projektpekao.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    void deleteById(Long id);
    User addUser(User user);
    User updateUser(User user);
}
