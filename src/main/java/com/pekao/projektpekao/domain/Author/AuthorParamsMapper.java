package com.pekao.projektpekao.domain.Author;

import com.pekao.projektpekao.controller.Author.AuthorDto;

import java.util.List;

public class AuthorParamsMapper {

        private AuthorParamsMapper() {
        }

        public static AuthorParams toAuthorParams(AuthorDto authorDto) {
            return AuthorParams.builder()
                    .firstName(authorDto.getFirstName())
                    .lastName(authorDto.getLastName())
                    .build();
        }

        public static List<AuthorParams> toCreateAuthorParamsList(List<AuthorDto> authorDtoList) {
            return authorDtoList.stream()
                    .map(AuthorParamsMapper::toAuthorParams)
                    .toList();
        }


    public static AuthorParams fromEntityToAuthorParams(Author author) {
        return AuthorParams.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }

    public static List<AuthorParams> fromEntityToAuthorParamsList(List<Author> authorList) {
        return authorList.stream()
                .map(AuthorParamsMapper::fromEntityToAuthorParams)
                .toList();
    }
}
