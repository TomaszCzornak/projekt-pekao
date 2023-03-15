package com.pekao.projektpekao.infrastructure;

import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    List<Comment> findAll();
    Optional<Comment> findById(Long id);
    Comment findCommentByBook(Book book);
    void deleteCommentById(Long id);
    Comment addComment(Comment comment);
    Comment updateComment(Comment comment);
}
