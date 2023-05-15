package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.controller.Author.AuthorDtoMapper;
import com.pekao.projektpekao.controller.Comments.CommentDtoMapper;
import com.pekao.projektpekao.domain.Book.Book;
import com.pekao.projektpekao.domain.Book.BookParams;

import java.util.List;

public final class BookDtoMapper {

    private BookDtoMapper() {
    }

    public static BookDto toBookDto(BookParams bookParams) {
        return BookDto.builder()
                .id(bookParams.getId())
                .authorDto(AuthorDtoMapper.fromParamsToAuthorDto(bookParams.getAuthorParams()))
                .title(bookParams.getTitle())
                .commentDtoList(CommentDtoMapper.toCommentsParams(bookParams.getCommentParamsList()))
                .publisher(bookParams.getPublisher())
                .build();

    }

    public static List<BookDto> toBookDtos(List<Book> bookList) {
        return bookList.stream()
                .map(BookDtoMapper::toBookDto)
                .toList();
    }

    public static BookDto toBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .authorDto(AuthorDtoMapper.fromParamsToAuthorDto(book.getAuthor()))
                .title(book.getTitle())
                .commentDtoList(CommentDtoMapper.toCommentsDto(book.getCommentList()))
                .publisher(book.getPublisher())
                .build();

    }
}
