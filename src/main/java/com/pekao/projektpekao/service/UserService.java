package com.pekao.projektpekao.service;

import com.pekao.projektpekao.entity.User;
import com.pekao.projektpekao.infrastructure.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource(name = "UserDaoJpaImpl")
    private final UserDao userDaoJpa;

    public UserService(UserDao userDaoJpa) {
        this.userDaoJpa = userDaoJpa;
    }

    public List findAllUsers() {
        return userDaoJpa.findAll();
    }

    public User findUserById(Long id) {
        return userDaoJpa.findById(id).orElseThrow(()->new IllegalStateException("Cannot find user with id " + id));
    }

    public User findUserByEmail(String email) {
        return userDaoJpa.findByEmail(email);
    }
    public void removeUserById(Long id) {
        userDaoJpa.deleteById(id);
    }

    public User addUser(User user) {
        return userDaoJpa.addUser(user);
    }

    public User updateUser(User user) {
        return userDaoJpa.updateUser(user);
    }

}
