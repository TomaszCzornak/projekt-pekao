package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.domain.Author.AuthorParams;
import com.pekao.projektpekao.domain.Book.Book;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class BookForCommentParams {

    private Long id;
    private String title;
    private AuthorParams authorParams;
    private Book.Publisher publisher;
}
