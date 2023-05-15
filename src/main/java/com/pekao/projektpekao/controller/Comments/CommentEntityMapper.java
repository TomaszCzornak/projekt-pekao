package com.pekao.projektpekao.controller.Comments;

import com.pekao.projektpekao.controller.Book.BookEntityMapper;
import com.pekao.projektpekao.controller.Book.BookForCommentEntityMapper;
import com.pekao.projektpekao.controller.Users.UserEntityMapper;
import com.pekao.projektpekao.domain.Comment.Comment;
import com.pekao.projektpekao.domain.Comment.CommentParams;

import java.util.List;
import java.util.stream.Collectors;

public final class CommentEntityMapper {

    private CommentEntityMapper() {}

    public static Comment toCommentEntity(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .content(commentDto.getContent())
                .user(UserEntityMapper.toUserEntity(commentDto.getUserDto()))
                .book(BookForCommentEntityMapper.toBookEntity(commentDto.getBookForCommentDto()))
                .build();
    }

    public static List<Comment> fromCommentDtoListToEntityList(List<CommentDto> commentDtoList) {
        return commentDtoList.stream()
                .map(CommentEntityMapper::toCommentEntity)
                .toList();
    }

    public static Comment toCommentEntity(CommentParams commentParams) {
        return Comment.builder()
                .content(commentParams.getContent())
                .user(UserEntityMapper.toUserEntity(commentParams.getUserParams()))
                .build();
    }

    public static List<Comment> fromCommentParamsToEntityList(List<CommentParams> commentParamsList) {
        return commentParamsList.stream()
                .map(CommentEntityMapper::toCommentEntity)
                .toList();

    }
}
