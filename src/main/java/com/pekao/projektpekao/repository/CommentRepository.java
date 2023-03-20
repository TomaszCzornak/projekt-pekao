package com.pekao.projektpekao.repository;

import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findCommentByContent(String comment);
}
