package com.pekao.projektpekao.service;

import com.pekao.projektpekao.entity.User;
import com.pekao.projektpekao.exception.NotFoundException;
import com.pekao.projektpekao.infrastructure.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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

    public Optional<User> findUserById(Long id) {
        return userDaoJpa.findById(id);
    }
    public void removeUserById(Long id) {
        userDaoJpa.deleteById(id);
    }

    public User addUser(User user) {
        return userDaoJpa.addUser(user);
    }

    public void updateUser(Long id, User user) {
        findUserOrThrow(id);
        userDaoJpa.updateUser(user);
    }

    private User findUserOrThrow(Long id) {
        return userDaoJpa.findById(id).orElseThrow(
                () -> new NotFoundException("Nie ma użytkownika o id " + id ));
    }
}
