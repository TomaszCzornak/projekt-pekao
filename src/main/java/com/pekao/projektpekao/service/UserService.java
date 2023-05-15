package com.pekao.projektpekao.service;

import com.pekao.projektpekao.controller.Users.UserEntityMapper;
import com.pekao.projektpekao.domain.User.User;
import com.pekao.projektpekao.domain.User.UserParams;
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

    public User addUser(UserParams userParams) {
        User userMapped = UserEntityMapper.toUserEntity(userParams);
        return userDaoJpa.addUser(userMapped);
    }

    public User updateUser(UserParams userParams) {
        User userEntityMapped = UserEntityMapper.toUserEntity(userParams);
        return userDaoJpa.updateUser(userEntityMapped);
    }

}
