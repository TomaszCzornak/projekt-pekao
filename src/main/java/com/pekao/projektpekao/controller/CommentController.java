package com.pekao.projektpekao.controller;

import com.pekao.projektpekao.entity.Comment;
import com.pekao.projektpekao.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public CommentResponse getAllComments() {
        List<CommentDto> commentDtoList = CommentDtoMapper.toCommentsDto(commentService.findAllComments());
        return CommentResponse.builder()
                .commentResponseList(commentDtoList)
                .build();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable("id") Long id) {
        return commentService.findCommentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        commentService.removeCommentById(id);
    }

    @PutMapping()
    public Comment postComment(@RequestBody Comment comment) {

        // \/ DTO           KLIENT UŻYWAJĄCY PRZEGLĄDARKI
        // \/ DTO/PARAMS    Controller                      /\ DTO/RESPONSE
        // \/ DTO/ENTITY    Service                         /\ DTO/PARAMS
        // \/ ENTITY        Repository                      /\ ENTITY/DTO
        //                  DB                              /\ ENTITY

        // Różnice między DTO/PARAMS/RESULT/REQUEST/RESPONSE : !!!NIE MA!!! tak samo jak nie ma różnic między @Bean/@Repository/@Component/@Service

        // mappery
        // mapper DTO -> ENTITY - service
        // mapper REQUEST/RESPONSE -> RESULT/PARAMS - controller
        return commentService.addComment(comment);
    }

    @PutMapping("/{id}")
    public void putComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        if (!Objects.equals(id, comment.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id does not match");
        }
        commentService.updateComment(comment);
    }

}
