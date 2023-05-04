package com.pekao.projektpekao.controller.Comments;

import com.pekao.projektpekao.controller.Author.AuthorDto;
import com.pekao.projektpekao.controller.Book.BookForCommentDto;
import com.pekao.projektpekao.controller.Users.UserDto;
import com.pekao.projektpekao.domain.Comment;

import java.util.List;
import java.util.stream.Collectors;

public class CommentDtoMapper {

    private CommentDtoMapper() {
    }

    public static CommentDto toCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userDto(UserDto.builder()
                        .id(comment.getUser().getId())
                        .firstName(comment.getUser().getFirstName())
                        .lastName(comment.getUser().getLastName())
                        .email(comment.getUser().getEmail())
                        .createdAt(comment.getUser().getCreatedAt())
                        .build())
                .bookForCommentDto(BookForCommentDto.builder()
                        .title(comment.getBook().getTitle())
                        .author(AuthorDto.builder()
                                .firstName(comment.getBook().getAuthor().getFirstName())
                                .lastName(comment.getBook().getAuthor().getLastName())
                                .build())
                        .build())
                .build();
    }

    public static List<CommentDto> toCommentsDto(List<Comment> commentList) {
        return commentList.stream()
                .map(CommentDtoMapper::toCommentDto)
                .collect(Collectors.toList());
    }
}
