package com.pekao.projektpekao.service;

import com.pekao.projektpekao.controller.Comments.CommentEntityMapper;
import com.pekao.projektpekao.domain.Comment.Comment;
import com.pekao.projektpekao.domain.Comment.CommentParams;
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

    public Comment addComment(CommentParams commentParams) {
        Comment commentEntity = CommentEntityMapper.toCommentEntity(commentParams);
        return commentDaoJpa.addComment(commentEntity);
    }

    public Comment updateComment(CommentParams commentParams) {
        Comment commentEntityMapped = CommentEntityMapper.toCommentEntity(commentParams);
        return commentDaoJpa.updateComment(commentEntityMapped);
    }

}
