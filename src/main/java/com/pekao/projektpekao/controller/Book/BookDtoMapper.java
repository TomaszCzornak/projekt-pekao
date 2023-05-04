package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.controller.Author.AuthorDto;
import com.pekao.projektpekao.controller.Comments.CommentDtoMapper;
import com.pekao.projektpekao.domain.book.Book;

import java.util.List;

public final class BookDtoMapper {

    private BookDtoMapper() {}

    public static BookDto toBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(AuthorDto.builder()
                        .id(book.getAuthor().getId())
                        .firstName(book.getAuthor().getFirstName())
                        .lastName(book.getAuthor().getLastName())
                        .build())
                .commentDtoList(CommentDtoMapper.toCommentsDto(book.getCommentList()))
                .publisher(book.getPublisher())
                .electronicJournal(book.getElectronicJournal())
                .build();

    }

    public static List<BookDto> toBookDtos(List<Book> bookList) {
        return bookList.stream()
                .map(BookDtoMapper::toBookDto)
                .toList();
    }

    }
