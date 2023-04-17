package com.pekao.projektpekao;

import com.pekao.projektpekao.entity.Author;

public class AuthorTestUtility {

    public static Author createAuthor(String firstName, String lastName) {
        return Author.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .buildNewEntity();
    }

}
