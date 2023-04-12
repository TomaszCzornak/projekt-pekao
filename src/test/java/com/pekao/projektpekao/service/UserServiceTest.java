package com.pekao.projektpekao.service;

import com.pekao.projektpekao.UserTestUtility;
import com.pekao.projektpekao.entity.Comment;
import com.pekao.projektpekao.entity.User;
import com.pekao.projektpekao.repository.CommentRepository;
import com.pekao.projektpekao.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
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
@TestPropertySource("/application-test.properties") //alternative in case of test issues
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
//        //given
//        Comment commentToSave1 = commentRepository.save(new Comment("Testowy komentarz 1"));
//        Comment commentToSave2 = commentRepository.save(new Comment("Testowy komentarz 2"));
//        Comment commentToSave3 = commentRepository.save(new Comment("Testowy komentarz 3"));
////        User user1 = new User("Testowe_Imie1", "Testowe_Nazwisko1", "tomek@gmail.com", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
////                .format(new Date()), List.of(commentToSave1));
////        User user2 = new User("Testowe_Imie2", "Testowe_Nazwisko2", "mareknowakowski@gmail.com", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
////                .format(new Date()), List.of(commentToSave2, commentToSave3));
//        User user1 = new User.Builder(1L, "Testowe_Imie1", "Testowe_Nazwisko1", "tomek@gmail.com").commentList(List.of(commentToSave1, commentToSave2)).build();
//        User user2 = new User.Builder(2L, "Testowe_Imie2", "Testowe_Nazwisko2", "mareknowakowski@gmail.com").commentList(List.of(commentToSave3)).build();
//
//        //when
//        List<User> usersFound = userRepository.saveAll(List.of(user1, user2));
//        //then
//        assertThat(usersFound, hasSize(2));

        //given
        final List<User> usersToSave = List.of(
                UserTestUtility.createUser1WithComments(), UserTestUtility.createUser2WithComments()
        );
        //when
        List<User> usersFound = userRepository.saveAll(usersToSave);
        //then
        assertThat(usersFound, hasSize(2));
        assertThat(
                usersFound.stream().anyMatch(n -> n.getFirstName().equals(usersToSave.get(0).getFirstName()))
        ).isTrue();
        assertThat(
                usersFound.stream().anyMatch(n -> n.getFirstName().equals(usersToSave.get(1).getFirstName()))
        ).isTrue();
    }

    @Test
    void findUserById() {
        //given
        User userToSave = new User.Builder(1L, "Testowe_Imie1", "Testowe_Nazwisko1", "tomek@gmail.com").build();
        User userSaved = userRepository.save(userToSave);
        //when
        User userFound = userService.findUserById(userSaved.getId());
        //then
        assertThat(userFound).extracting("firstName", "lastName", "email")
                .doesNotContainNull()
                .containsExactly("Testowe_Imie1", "Testowe_Nazwisko1", "tomek@gmail.com");
    }

    @Test
    void findUserByEmail() {
        //given
        User userToSave = new User.Builder(1L, "Tomasz", "Czornak", "tomek@gmail.com").build();
        User userSaved = userRepository.save(userToSave);
        //when
        User userFound = userService.findUserByEmail("tomek@gmail.com");
        //then
        assertEquals(userSaved.getEmail(), userFound.getEmail());
    }

    @Test
    void removeUserById() {
        //given
        User userToSave = new User.Builder(1L, "Tomasz", "Czornak", "tomek@gmail.com").build();
        User userSaved = userRepository.save(userToSave);
        //when
        userService.removeUserById(userSaved.getId());
        //then
        assertThrows(IllegalStateException.class, () -> userService.findUserById(userSaved.getId()));
    }

    @Test
    void addUser() {
        //given
        User userToSave = new User.Builder(1L, "Marek", "Nowak", "marek@nowak.com").build();
        //when
        User userSaved = userService.addUser(userToSave);
        //then
        assertThat(userSaved).extracting("firstName", "lastName", "email")
                .doesNotContainNull()
                .containsExactly("Marek", "Nowak", "marek@nowak.com");
    }

    @Test
    void updateUser() {
//        //given
//        User userToSave = new User.Builder(1L, "Marek", "Nowak", "marek@nowak.com").commentList(List.of(new Comment("Testowy komentarz"))).build();
//        User userSaved = userRepository.save(userToSave);
//        User userChanged = new User.Builder(1L, "Marek", "Nowak", "marlon@nowak.com").commentList(List.of(new Comment("Testowy komentarz"))).build();
//        //when
//        User userFound = userService.updateUser(userChanged.getId(), userChanged);
//        //then
//        assertEquals(userFound.getEmail(), userChanged.getEmail());

        //given
        User userSaved = userRepository.save(UserTestUtility.createUser1WithComments());
        User userWithDataToSave = User.builder()
                .from(userSaved)
                .email(userSaved.getEmail() + ".org")
                .build();
        //when
        User userFound = userService.updateUser(userWithDataToSave);
        //then
        assertEquals(userFound.getEmail(), userWithDataToSave.getEmail());
    }
}
