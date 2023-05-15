package com.pekao.projektpekao;

import com.pekao.projektpekao.domain.Author.AuthorParams;
import com.pekao.projektpekao.domain.Author.Author;

public class AuthorTestUtility {

    public static AuthorParams createAuthor(String firstName, String lastName) {
        return AuthorParams.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
    public static Author createAuthorEntity(String firstName, String lastName) {
        return Author.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .buildNewEntity();
    }
    public static Author createdAuthorWithFirstNameAndLastName(String firstName, String lastName) {
        return Author.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .buildNewEntity();
    }

}
