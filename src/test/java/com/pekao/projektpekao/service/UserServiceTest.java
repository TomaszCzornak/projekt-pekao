package com.pekao.projektpekao.service;

import com.pekao.projektpekao.UserTestUtility;
import com.pekao.projektpekao.controller.Users.UserDto;
import com.pekao.projektpekao.controller.Users.UserDtoMapper;
import com.pekao.projektpekao.domain.User.User;
import com.pekao.projektpekao.domain.User.UserParams;
import com.pekao.projektpekao.domain.User.UserParamsMapper;
import com.pekao.projektpekao.repository.CommentRepository;
import com.pekao.projektpekao.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
//@TestPropertySource("/application-test.properties") //alternative in case of test issues
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    @AfterEach
    void tearDown() {
        commentRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findAllUsers() {
        //given
        final List<User> usersToSave = List.of(
                UserTestUtility.createUser1WithComments(), UserTestUtility.createUser2WithComments()
        );
        //when
        List<User> usersFound = userRepository.saveAll(usersToSave);
        //then
        assertThat(usersFound, hasSize(2));
        assertThat(
                usersFound.stream().anyMatch(user -> user.getFirstName().equals(usersToSave.get(0).getFirstName()))
        ).isTrue();
        assertThat(
                usersFound.stream().anyMatch(n -> n.getFirstName().equals(usersToSave.get(1).getFirstName()))
        ).isTrue();
    }

    @Test
    void findUserById() {
        //given
        User userEntiySaved = userRepository.save(UserTestUtility.createUser("Johny","Walker"));
        UserDto userDtoMapped = UserDtoMapper.toUserDto(userEntiySaved);
        UserParams userToSave = UserParamsMapper.toUserParams(userDtoMapped);
        User userSaved = userService.addUser(userToSave);
        //when
        User userFound = userService.findUserById(userSaved.getId());
        //then
        assertEquals(userSaved.getId(), userFound.getId());

    }

    @Test
    void findUserByEmail() {
        //given
        User userSaved = userRepository.save(UserTestUtility.createUserWithEmail());//adam.kowalski@gmail.com
        //when
        User userFound = userService.findUserByEmail("adam.kowalski@gmail.com");
        //then
        assertEquals(userSaved.getEmail(), userFound.getEmail());
    }

    @Test
    void removeUserById() {
        //given
        User userEntitySaved = userRepository.save(UserTestUtility.createUser("Johny","Walker"));
        UserParams userToSave = UserParamsMapper.fromEntityToUserParams(userEntitySaved);
        User userSaved = userService.addUser(userToSave);
        //when

        userService.removeUserById(userSaved.getId());
        //then
        assertThrows(IllegalStateException.class, () -> userService.findUserById(userSaved.getId()));
    }

    @Test
    void addUser() {
        //given
        User userEntitySaved = userRepository.save(UserTestUtility.createUser("Johny","Walker"));
        UserDto userDtoMapped = UserDtoMapper.toUserDto(userEntitySaved);
        UserParams userToSave = UserParamsMapper.toUserParams(userDtoMapped);
        //when
        User userSaved = userService.addUser(userToSave);
        //then
        assertThat(userSaved).extracting("firstName", "lastName")
                .doesNotContainNull()
                .containsExactly("Johny", "Walker");
    }

    @Test
    void updateUser() {
        //given
        User userEntitySaved = userRepository.save(UserTestUtility.createUser("Johny","Walker"));
        User userWithDataToSave = User.builder()
                .from(userEntitySaved)
                .email(userEntitySaved.getEmail() + ".org")
                .build();
        UserDto userDto = UserDtoMapper.toUserDto(userWithDataToSave);
        UserParams userParams = UserParamsMapper.toUserParams(userDto);
        //when
        User userFound = userService.updateUser(userParams);
        //then
        assertEquals(userFound.getEmail(), userWithDataToSave.getEmail());
    }
}
