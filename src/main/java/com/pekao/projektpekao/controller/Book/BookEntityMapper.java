package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.controller.Comments.CommentEntityMapper;
import com.pekao.projektpekao.domain.Author;
import com.pekao.projektpekao.domain.book.Book;

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
