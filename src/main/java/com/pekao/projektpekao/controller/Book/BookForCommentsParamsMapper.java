package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.domain.Author.AuthorParamsMapper;
import com.pekao.projektpekao.domain.Book.Book;

public class BookForCommentsParamsMapper {

    public static BookForCommentParams toParams(Book book) {
        return BookForCommentParams.builder()
                .title(book.getTitle())
                .authorParams(AuthorParamsMapper.fromEntityToAuthorParams(book.getAuthor()))
                .publisher(book.getPublisher())
                .build();
    }


}
