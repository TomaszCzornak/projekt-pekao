package com.pekao.projektpekao.service;

import com.pekao.projektpekao.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static com.pekao.projektpekao.service.UserService.EventType.X;
import static com.pekao.projektpekao.service.UserService.EventType.Y;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


// Test integracyjny
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        // stworzyc dane
        System.out.println("setUp");
    }

    @AfterEach
    void tearDown() {
        // wyczyscic
        System.out.println("tearDown");
    }

    @Test
    void findAllUsers() {
        // given
        // when
        User user = userService.addUser(new User("Adrian", "Kowalski", "xyz@gmail.com", "2023-01-01", List.of()));
        // then
        assertThat(user).extracting(User::getFirstName, User::getLastName, User::getEmail)
                .containsExactly("Adrian", "Kowalski", "xyz@gmail.com");
    }

    @Test
    void shoudlCreateUserWhenEventTypeIsY() {
        // given
        UserService.EventType eventType = Y;
        // when
        User user = userService.createUser(eventType);
        // then
        assertThat(user);
    }

    @Test
    void shoudlCreateUserWhenEventTypeIsX() {
        // given
        UserService.EventType eventType = X;
        // when
        User user = userService.createUser(eventType);
        // then
        assertThat(user);
    }

    @Test
    void findUserById() {
    }

    @Test
    void findUserByEmail() {
    }

    @Test
    void removeUserById() {
    }

    @Test
    void addUser() {
    }

    @Test
    void updateUser() {
    }
}
