package com.pekao.projektpekao.controller.Author;

import com.pekao.projektpekao.controller.Book.BookAuthorDtoMapper;
import com.pekao.projektpekao.domain.Author.Author;

import java.util.List;

public class AuthorWithBooksDtoMapper {

    private AuthorWithBooksDtoMapper(){}

    public static AuthorWithBooksDto toAuthorWithBooksDto(Author author) {
        return AuthorWithBooksDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .bookAuthorDtoList(BookAuthorDtoMapper.toBookAuthorDtoList(author.getBookList()))
                .build();
    }

    public static List<AuthorWithBooksDto> toAuthorWithBooksDto(List<Author> authorList) {
        return authorList.stream()
                .map(AuthorWithBooksDtoMapper::toAuthorWithBooksDto)
                .toList();
    }

}
