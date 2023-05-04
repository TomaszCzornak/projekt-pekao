package com.pekao.projektpekao.controller.Comments;

import com.pekao.projektpekao.controller.Book.BookForCommentEntityMapper;
import com.pekao.projektpekao.controller.Users.UserEntityMapper;
import com.pekao.projektpekao.domain.Comment;

import java.util.List;
import java.util.stream.Collectors;

public final class CommentEntityMapper {

    private CommentEntityMapper() {}

    public static Comment toCommentEntity(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .content(commentDto.getContent())
                .user(UserEntityMapper.userEntity(commentDto.getUserDto()))
                .book(BookForCommentEntityMapper.toBookEntity(commentDto.getBookForCommentDto()))
                .build();
    }

    public static List<Comment> toCommentListEntity(List<CommentDto> commentList) {
        return commentList.stream()
                .map(CommentEntityMapper::toCommentEntity)
                .collect(Collectors.toList());
    }

}
