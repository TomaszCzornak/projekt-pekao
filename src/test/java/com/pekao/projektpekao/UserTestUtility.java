package com.pekao.projektpekao;

import com.pekao.projektpekao.domain.Comment.Comment;
import com.pekao.projektpekao.domain.User.User;
import com.pekao.projektpekao.domain.User.UserParams;

import java.time.LocalDate;
import java.util.List;

public class UserTestUtility {

    public static User createUser(String firstName, String lastName) {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    public static User createUser1WithComments() {
        return createUser(
                "Adam",
                "Kowalski",
                "adam.kowalski@gmail.com",
                List.of(Comment.builder()
                        .content("Testowy Komentarz")
                        .build()
                )
        );
    }

    public static User createUser2WithComments() {
        return createUser(
                "Damian",
                "Michalak",
                "damian.michalak@gmail.com",
                List.of(Comment.builder()
                        .content("Testowy Komentarz 1")
                        .buildNewEntity(),
                        Comment.builder()
                                .content("Testowy Komentarz 2")
                                .buildNewEntity()
                )
        );
    }

    private static User createUser(String firstName, String lastName, String email, List<Comment> comments) {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .commentList(comments)
                .createdAt(LocalDate.now())
                .buildNewEntity();
    }

    private static User createUser(String firstName, String lastName, String email) {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .createdAt(LocalDate.now())
                .buildNewEntity();
    }

    public static User createUserWithEmail() {
        return createUser(
                "Adam",
                "Kowalski",
                "adam.kowalski@gmail.com");
    }
    public static UserParams createParamsUser(String firstName, String lastName) {
        return UserParams.builder()
                .firstName(firstName)
                .lastName(lastName)
                .createdAt(LocalDate.now())
                .email("Lorraine.Garcia@onet.pl")
                .build();
    }
}
