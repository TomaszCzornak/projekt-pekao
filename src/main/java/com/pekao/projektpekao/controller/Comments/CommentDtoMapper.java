package com.pekao.projektpekao.controller.Comments;

import com.pekao.projektpekao.controller.Author.AuthorDto;
import com.pekao.projektpekao.controller.Book.BookForCommentDto;
import com.pekao.projektpekao.controller.Users.UserDto;
import com.pekao.projektpekao.domain.Comment.Comment;
import com.pekao.projektpekao.domain.Comment.CommentParams;

import java.util.List;

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
                        .authorDto(AuthorDto.builder()
                                .firstName(comment.getBook().getAuthor().getFirstName())
                                .lastName(comment.getBook().getAuthor().getLastName())
                                .build())
                        .publisher(comment.getBook().getPublisher())
                        .build())
                .build();
    }

    public static List<CommentDto> toCommentsDto(List<Comment> commentList) {
        return commentList.stream()
                .map(CommentDtoMapper::toCommentDto)
                .toList();
    }

    public static CommentDto toCommentDto(CommentParams commentParams) {
        return CommentDto.builder()
                .content(commentParams.getContent())
                .userDto(UserDto.builder()
                        .id(commentParams.getUserParams().getId())
                        .firstName(commentParams.getUserParams().getFirstName())
                        .lastName(commentParams.getUserParams().getLastName())
                        .email(commentParams.getUserParams().getEmail())
                        .createdAt(commentParams.getUserParams().getCreatedAt())
                        .build())
                .bookForCommentDto(BookForCommentDto.builder()
                        .title(commentParams.getBookParams().getTitle())
                        .authorDto(AuthorDto.builder()
                                .firstName(commentParams.getBookParams().getAuthorParams().getFirstName())
                                .lastName(commentParams.getBookParams().getAuthorParams().getLastName())
                                .build())
                        .publisher(commentParams.getBookParams().getPublisher())
                        .build())
                .build();
    }
    public static List<CommentDto> toCommentsParams(List<CommentParams> commentParamsList) {
        return commentParamsList.stream()
                .map(CommentDtoMapper::toCommentDto)
                .toList();
    }
}
