package com.pekao.projektpekao.infrastructure;

import com.pekao.projektpekao.domain.Comment.Comment;
import com.pekao.projektpekao.repository.CommentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("CommentDaoJpaImpl")
public class CommentDaoJpaImpl implements CommentDao {

    private final CommentRepository commentRepository;

    public CommentDaoJpaImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findCommentById(id);
    }


    @Override
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findCommentByContent(String content) {
        return commentRepository.findCommentByContent(content);
    }
}
