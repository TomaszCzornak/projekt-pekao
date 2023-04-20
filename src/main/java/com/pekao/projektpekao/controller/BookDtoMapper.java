package com.pekao.projektpekao.controller;

import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.entity.BookDto;

import java.util.List;
import java.util.stream.Collectors;

public class BookDtoMapper {

    public static BookDto toBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .commentList(book.getCommentList())
                .publisher(book.getPublisher())// TODO: 20.04.2023  
                .electronicJournal(book.getElectronicJournal())
                .build();

    }

    public static List<BookDto> bookDtos (List<Book> bookList) {
        return bookList.stream()
                .map(BookDtoMapper::toBookDto)
                .collect(Collectors.toList());
    }

}
