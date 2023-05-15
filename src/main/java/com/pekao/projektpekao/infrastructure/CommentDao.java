package com.pekao.projektpekao.infrastructure;

import com.pekao.projektpekao.domain.Comment.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    List<Comment> findAll();
    Optional<Comment> findById(Long id);
    void deleteCommentById(Long id);
    Comment addComment(Comment comment);
    Comment updateComment(Comment comment);
    Optional<Comment> findCommentByContent(String content);
}
