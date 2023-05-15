package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.domain.Author.Author;
import com.pekao.projektpekao.domain.Book.Book;
import com.pekao.projektpekao.domain.Book.BookParams;

import java.util.List;


public class BookForCommentEntityMapper {

    private BookForCommentEntityMapper() {
    }

    public static Book toBookEntity(BookForCommentDto bookForCommentDto) {
        return Book.builder()
                .title(bookForCommentDto.getTitle())
                .author(Author.builder()
                        .withFirstName(bookForCommentDto.getAuthorDto().getFirstName())
                        .withLastName(bookForCommentDto.getAuthorDto().getLastName())
                        .buildNewEntity())
                .publisher(bookForCommentDto.getPublisher())
                .build();
    }

    public static List<Book> toBooksEntity(List<BookForCommentDto> bookForCommentDtoList) {
        return bookForCommentDtoList.stream()
                .map(BookForCommentEntityMapper::toBookEntity)
                .toList();
    }

    public static Book toBookEntity(BookParams bookParams) {
        return Book.builder()
                .title(bookParams.getTitle())
                .author(Author.builder()
                        .withFirstName(bookParams.getAuthorParams().getFirstName())
                        .withLastName(bookParams.getAuthorParams().getLastName())
                        .buildNewEntity())
                .publisher(bookParams.getPublisher())
                .build();
    }
}
