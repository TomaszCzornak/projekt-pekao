package com.pekao.projektpekao;

import com.pekao.projektpekao.entity.Comment;
import com.pekao.projektpekao.entity.User;

import java.time.LocalDate;
import java.util.List;

public class UserTestUtility {

    public static User createUser(String firstName, String lastName) {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .createdAt(LocalDate.now())
                .buildNewEntity();
    }

    public static User createUser1WithComments() {
        return createUser(
                "Adam",
                "Kowalski",
                "adam.kowalski@gmail.com",
                List.of(
                        CommentTestUtility.createComment(),
                        CommentTestUtility.createComment()
                )
        );
    }

    public static User createUser2WithComments() {
        return createUser(
                "Damian",
                "Michalak",
                "damian.michalak@gmail.com",
                List.of(
                        CommentTestUtility.createComment(),
                        CommentTestUtility.createComment()
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
}