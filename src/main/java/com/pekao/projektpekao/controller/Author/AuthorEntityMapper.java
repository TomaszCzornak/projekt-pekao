package com.pekao.projektpekao.controller.Author;

import com.pekao.projektpekao.domain.Author;

import java.util.List;

public class AuthorEntityMapper {

    private AuthorEntityMapper(){}

    public static Author toAuthorEntity(AuthorDto authorDto) {
        return Author.builder()
                .withId(authorDto.getId())
                .withFirstName(authorDto.getFirstName())
                .withLastName(authorDto.getLastName())
                .build();

    }
    public static Author toAuthorEntityNew(AuthorDto authorDto) {
        return Author.builder()
                .withId(authorDto.getId())
                .withFirstName(authorDto.getFirstName())
                .withLastName(authorDto.getLastName())
                .buildNewEntity();

    }
    public static List<Author> toAuthorsEntity(List<AuthorDto> authorDtoList) {
        return authorDtoList.stream()
                .map(AuthorEntityMapper::toAuthorEntity)
                .toList();
    }

}
