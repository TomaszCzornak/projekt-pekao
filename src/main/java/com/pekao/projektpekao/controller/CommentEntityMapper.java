package com.pekao.projektpekao.controller;

import com.pekao.projektpekao.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

public final class CommentEntityMapper {

    private CommentEntityMapper() {}

    public static Comment toCommentEntity(com.pekao.projektpekao.controller.CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .content(commentDto.getContent())
                .build();
    }

    public static List<Comment> toCommentListEntity(List<CommentDto> commentList) {
        return commentList.stream()
                .map(CommentEntityMapper::toCommentEntity)
                .collect(Collectors.toList());
    }

}
