package com.pekao.projektpekao.domain.Book;
import com.pekao.projektpekao.domain.Author.AuthorParams;
import com.pekao.projektpekao.domain.Comment.CommentParams;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder(toBuilder = true)
@Getter
public class BookParams {
    private final Long id;
    private final String title;
    private final AuthorParams authorParams;
    private final List<CommentParams> commentParamsList;
    private final Book.Publisher publisher;

    public BookParams(Long id, String title, AuthorParams authorParams, List<CommentParams> commentParamsList, Book.Publisher publisher) {
        this.id = id;
        this.title = title;
        this.authorParams = authorParams;
        this.commentParamsList = commentParamsList;
        this.publisher = publisher;
    }


}
