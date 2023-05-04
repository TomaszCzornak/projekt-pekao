package com.pekao.projektpekao.service;

import com.pekao.projektpekao.domain.Comment;
import com.pekao.projektpekao.infrastructure.CommentDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentService {

    @Resource(name = "CommentDaoJpaImpl")
    private final CommentDao commentDaoJpa;

    public CommentService(CommentDao commentDaoJpa) {
        this.commentDaoJpa = commentDaoJpa;
    }

    public List<Comment> findAllComments() {
        return commentDaoJpa.findAll();
    }

    public Comment findCommentById(Long id) {
        return commentDaoJpa.findById(id).orElseThrow(() -> new IllegalStateException("Cannot find comment with id " + id));
    }

    public Comment findCommentByContent(String content) {
        return commentDaoJpa.findCommentByContent(content).orElseThrow(() -> new IllegalStateException("Cannot find comment with content " + content));
    }

    public void removeCommentById(Long id) {
        commentDaoJpa.deleteCommentById(id);
    }

    public Comment addComment(Comment comment) {
        return commentDaoJpa.addComment(comment);
    }

    public Comment updateComment(Comment comment) {
        return commentDaoJpa.updateComment(comment);
    }

}
