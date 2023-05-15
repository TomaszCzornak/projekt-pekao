package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.controller.Author.AuthorDto;
import com.pekao.projektpekao.controller.Comments.CommentDto;
import com.pekao.projektpekao.domain.Book.Book;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class BookDto {

    private Long id;
    private String title;
    private AuthorDto authorDto;
    private List<CommentDto> commentDtoList;
    private Book.Publisher publisher;



    protected BookDto() {
    }

    public BookDto(Long id, String title, AuthorDto authorDto, List<CommentDto> commentDtoList, Book.Publisher publisher) {
        this.id = id;
        this.title = title;
        this.authorDto = authorDto;
        this.commentDtoList = commentDtoList;
        this.publisher = publisher;
    }
}

