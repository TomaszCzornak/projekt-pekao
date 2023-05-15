package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.controller.Author.AuthorDto;
import com.pekao.projektpekao.domain.Book.Book;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookForCommentDto {

    private String title;
    private AuthorDto authorDto;
    private Book.Publisher publisher;


}

