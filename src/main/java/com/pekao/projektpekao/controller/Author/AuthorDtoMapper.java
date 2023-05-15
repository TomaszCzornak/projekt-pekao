package com.pekao.projektpekao.controller.Author;

import com.pekao.projektpekao.domain.Author.Author;
import com.pekao.projektpekao.domain.Author.AuthorParams;

import java.util.List;

public class AuthorDtoMapper {
    private AuthorDtoMapper(){}

    public static AuthorDto fromParamsToAuthorDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }

    public static List<AuthorDto> toAuthorDtoList(List<Author> authorList) {
        return authorList.stream()
                .map(AuthorDtoMapper::fromParamsToAuthorDto)
                .toList();
    }



    public static AuthorDto fromParamsToAuthorDto(AuthorParams authorParams) {
        return AuthorDto.builder()
                .firstName(authorParams.getFirstName())
                .lastName(authorParams.getLastName())
                .build();
    }

    public static List<AuthorDto> fromParamsListToAuthorDtoList(List<AuthorParams> authorParamsList) {
        return authorParamsList.stream()
                .map(AuthorDtoMapper::fromParamsToAuthorDto)
                .toList();
    }
}
