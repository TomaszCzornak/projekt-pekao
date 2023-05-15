package com.pekao.projektpekao.domain.Book;

import com.pekao.projektpekao.controller.Book.BookDto;
import com.pekao.projektpekao.controller.Book.BookForCommentDto;
import com.pekao.projektpekao.controller.Book.BookForCommentParams;
import com.pekao.projektpekao.domain.Author.AuthorParamsMapper;
import com.pekao.projektpekao.domain.Comment.CommentParamsMapper;

import java.util.List;

public class BookParamsMapper {

    private BookParamsMapper() {
    }

    public static BookParams toCreateBookParams(BookDto bookDto) {
        return BookParams.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .authorParams(AuthorParamsMapper.toAuthorParams(bookDto.getAuthorDto()))                .commentParamsList(CommentParamsMapper.toCreateCommentParamsList(bookDto.getCommentDtoList()))
                .publisher(bookDto.getPublisher())
                .build();
    }

    public static List<BookParams> toCreateBookParamsList(List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(BookParamsMapper::toCreateBookParams)
                .toList();
    }
    public static BookParams toCreateBookParams(BookForCommentDto bookForCommentDto) {
        return BookParams.builder()
                .title(bookForCommentDto.getTitle())
                .authorParams(AuthorParamsMapper.toAuthorParams(bookForCommentDto.getAuthorDto()))
                .publisher(bookForCommentDto.getPublisher())
                .build();
    }

    public static BookParams fromEntityToParams(Book book) {
        return BookParams.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorParams(AuthorParamsMapper.fromEntityToAuthorParams(book.getAuthor()))
                .commentParamsList(CommentParamsMapper.fromEntityToCommentParamList(book.getCommentList()))
                .publisher(book.getPublisher())
                .build();
    }
    public static BookParams fromBooksForComment(BookForCommentParams bookForCommentParams) {
        return BookParams.builder()
                .id(bookForCommentParams.getId())
                .title(bookForCommentParams.getTitle())
                .publisher(bookForCommentParams.getPublisher())
                .build();
    }

}
