package com.pekao.projektpekao.repository;

import com.pekao.projektpekao.domain.Comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findCommentByContent(String comment);
    Optional<Comment> findCommentById(Long id);


    void deleteAll();
}
