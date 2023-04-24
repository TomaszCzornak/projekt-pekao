package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.entity.Author;
import com.pekao.projektpekao.entity.Book;

import java.util.List;


public class BookForCommentEntityMapper {

    private BookForCommentEntityMapper() {
    }

    public static Book toBookEntity(BookForCommentDto bookForCommentDto) {
        return Book.builder()
                .id(bookForCommentDto.getId())
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
}
