package com.pekao.projektpekao;

import com.pekao.projektpekao.entity.User;
import com.pekao.projektpekao.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {

    private final UserRepository userRepository;

    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void saveUser() {
        userRepository.save(new User("Lucy", "Smith", "Lucy@gmail.com", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
                .format(new Date())));
        assertThat(userRepository.findUserByEmail("Lucy@gmail.com").isPresent()).isTrue();
    }

    @Test
    void deleteUser() {
        userRepository.save(new User("Lisa", "Morrison", "Lisa@gmail.com", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
                .format(new Date())));
        userRepository.deleteAll();
        assertThat(userRepository.count()).isEqualTo(0);
    }

}
