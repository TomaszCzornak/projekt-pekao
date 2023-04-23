package com.pekao.projektpekao.controller;

import com.pekao.projektpekao.entity.Author;
import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.entity.Comment;

import java.util.List;


public class BookEntityMapper {

    private BookEntityMapper() {
    }

    public static Book toBookEntity(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .author(Author.builder()
                        .withFirstName(bookDto.getAuthorDto().getFirstName())
                        .withLastName(bookDto.getAuthorDto().getLastName())
                        .buildNewEntity())
                .publisher(bookDto.getPublisher())
                .commentList(CommentEntityMapper.toCommentListEntity(bookDto.getcommentDtoList()))
                .electronicJournal(bookDto.getElectronicJournal())
                .build();
    }

    public static List<Book> toBooksEntity(List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(BookEntityMapper::toBookEntity)
                .toList();
    }
}
