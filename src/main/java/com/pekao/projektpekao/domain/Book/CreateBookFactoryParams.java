package com.pekao.projektpekao.domain.Book;

import com.pekao.projektpekao.controller.Author.AuthorDto;
import com.pekao.projektpekao.domain.Comment.Comment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CreateBookFactoryParams {

    private final Long id;
    private final String title;
    private final AuthorDto authorDto;
    private final List<Comment> commentDtoList;
    private final Book.Publisher publisher;



    public CreateBookFactoryParams(Long id, String title, AuthorDto authorDto, List<Comment> commentDtoList, Book.Publisher publisher) {
        if (id == null || title == null || authorDto == null || commentDtoList == null || publisher == null) {
            throw new IllegalStateException("On of required values is null: [%s]".formatted(List.of(id, title, authorDto, commentDtoList, publisher)));
        }

        this.id = id;
        this.title = title;
        this.authorDto = authorDto;
        this.commentDtoList = commentDtoList;
        this.publisher = publisher;
    }
}
