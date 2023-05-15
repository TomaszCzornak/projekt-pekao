package com.pekao.projektpekao.domain.Author;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
public class AuthorParams {

    private final Long id;
    private final String firstName;
    private final String lastName;



    public AuthorParams(Long id, String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new IllegalStateException("One of required values is null: [%s]".formatted(List.of(firstName, lastName)));
        }

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
