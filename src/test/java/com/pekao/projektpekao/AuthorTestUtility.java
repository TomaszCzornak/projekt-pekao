package com.pekao.projektpekao;

import com.pekao.projektpekao.domain.Author;

public class AuthorTestUtility {

    public static Author createAuthor(String firstName, String lastName) {
        return Author.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .buildNewEntity();
    }

}
