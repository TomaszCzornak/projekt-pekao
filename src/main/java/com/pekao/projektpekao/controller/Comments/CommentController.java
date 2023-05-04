package com.pekao.projektpekao.controller.Comments;

import com.pekao.projektpekao.domain.Comment;
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
    public CommentsResponse getAllComments() {
        List<CommentDto> commentDtoList = CommentDtoMapper.toCommentsDto(commentService.findAllComments());
        return CommentsResponse.builder()
                .commentsResponseList(commentDtoList)
                .build();
    }

    @GetMapping("/{id}")
    public CommentResponse getCommentById(@PathVariable("id") Long id) {
        CommentDto singleComment = CommentDtoMapper.toCommentDto(commentService.findCommentById(id));
        return CommentResponse.builder()
                .commentResponse(singleComment)
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        commentService.removeCommentById(id);
    }

    @PostMapping()
    public CommentResponse postComment(@RequestBody CommentDto commentDto) {
        Comment commentToPost = CommentEntityMapper.toCommentEntity(commentDto);
        Comment commentSaved = commentService.addComment(commentToPost);
        CommentDto commentDtoMapped = CommentDtoMapper.toCommentDto(commentSaved);
        return CommentResponse.builder()
                .commentResponse(commentDtoMapped)
                .build();
    }

    @PutMapping("/{id}")
    public void putComment(@PathVariable("id") Long id, @RequestBody CommentDto commentDto) {
        if (!Objects.equals(id, commentDto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id does not match");
        }
        Comment commentToPut = CommentEntityMapper.toCommentEntity(commentDto);
        commentService.updateComment(commentToPut);
    }

}
