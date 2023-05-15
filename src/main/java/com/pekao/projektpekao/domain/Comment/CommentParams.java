package com.pekao.projektpekao.domain.Comment;

import com.pekao.projektpekao.domain.User.UserParams;
import com.pekao.projektpekao.domain.Book.BookParams;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Setter
@Getter
public class CommentParams {

    private final String content;
    private final UserParams userParams;
    private final BookParams bookParams;

    public CommentParams(String content, UserParams userParams, BookParams bookParams) {
        this.content = content;
        this.userParams = userParams;
        this.bookParams = bookParams;
    }
}
