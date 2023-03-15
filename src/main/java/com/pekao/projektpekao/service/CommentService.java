package com.pekao.projektpekao.service;

import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.entity.Comment;
import com.pekao.projektpekao.exception.NotFoundException;
import com.pekao.projektpekao.infrastructure.CommentDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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
    public Optional<Comment> findCommentById(Long id) {
        return commentDaoJpa.findById(id);
    }
    public Comment findCommentByBook(Book book) {
        return commentDaoJpa.findCommentByBook(book);
    }
    public void removeCommentById(Long id) {
        commentDaoJpa.deleteCommentById(id);
    }
    public Comment addComment(Comment comment) {
        return commentDaoJpa.addComment(comment);
    }
    public void updateComment(Long id, Comment comment) {
        findCommentOrThrow(id);
        commentDaoJpa.updateComment(comment);
    }
    private Comment findCommentOrThrow(Long id) {
        return commentDaoJpa.findById(id).orElseThrow(
                () -> new NotFoundException("Nie ma komentarza o id " + id ));
    }
}
