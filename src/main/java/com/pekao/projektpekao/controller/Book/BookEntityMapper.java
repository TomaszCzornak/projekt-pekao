package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.controller.Comments.CommentEntityMapper;
import com.pekao.projektpekao.domain.Author.Author;
import com.pekao.projektpekao.domain.Book.Book;
import com.pekao.projektpekao.domain.Book.BookParams;

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
                .commentList(CommentEntityMapper.fromCommentDtoListToEntityList(bookDto.getCommentDtoList()))
                .build();
    }

    public static List<Book> toBooksEntity(List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(BookEntityMapper::toBookEntity)
                .toList();
    }
    public static Book toBookEntity(BookParams bookParams) {
        return Book.builder()
                .title(bookParams.getTitle())
                .author(Author.builder()
                        .withId(bookParams.getAuthorParams().getId())
                        .withFirstName(bookParams.getAuthorParams().getFirstName())
                        .withLastName(bookParams.getAuthorParams().getLastName())
                        .build())
                .publisher(bookParams.getPublisher())
                .commentList(CommentEntityMapper.fromCommentParamsToEntityList(bookParams.getCommentParamsList()))
                .build();

    }
    public static List<Book> toBookEntityList(List<BookParams> bookParamsList) {
        return bookParamsList.stream()
                .map(BookEntityMapper::toBookEntity)
                .toList();
    }
    public static Book toBookEntityTest(BookParams bookParams) {
        return Book.builder()
                .id(bookParams.getId())
                .title(bookParams.getTitle())
                .publisher(bookParams.getPublisher())
                .commentList(CommentEntityMapper.fromCommentParamsToEntityList(bookParams.getCommentParamsList()))
                .build();

    }
}
