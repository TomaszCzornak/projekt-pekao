package com.pekao.projektpekao.controller.Author;

import com.pekao.projektpekao.controller.Book.BookAuthorDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AuthorWithBooksDto {

    private Long id;
    private String firstName;
    private String lastName;
    private List<BookAuthorDto> bookAuthorDtoList;

}
