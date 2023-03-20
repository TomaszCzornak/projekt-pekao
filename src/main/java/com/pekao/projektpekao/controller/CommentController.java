package com.pekao.projektpekao.controller;

import com.pekao.projektpekao.entity.Comment;
import com.pekao.projektpekao.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public List<Comment> getAllComments() {
        return commentService.findAllComments();
    }
    @GetMapping("/{id}")
    public Optional<Comment> getCommentById(@PathVariable("id") Long id) {
        return commentService.findCommentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        commentService.removeCommentById(id);
    }
    @PutMapping()
    public Comment postComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }
    @PutMapping("/{id}")
    public void putComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        if(!Objects.equals(id, comment.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id does not match");
        }
        commentService.updateComment(id, comment);
    }

}
