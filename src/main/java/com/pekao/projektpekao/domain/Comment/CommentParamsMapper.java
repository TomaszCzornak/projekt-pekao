package com.pekao.projektpekao.domain.Comment;

import com.pekao.projektpekao.controller.Comments.CommentDto;
import com.pekao.projektpekao.domain.User.UserParamsMapper;

import java.util.List;

public class CommentParamsMapper {

    private CommentParamsMapper() {
    }

    public static CommentParams toCommentParams(CommentDto commentDto) {
        return CommentParams.builder()
                .content(commentDto.getContent())
                .userParams(UserParamsMapper.toUserParams(commentDto.getUserDto()))
                .build();
    }

    public static List<CommentParams> toCreateCommentParamsList(List<CommentDto> commentDtoList) {
        return commentDtoList.stream()
                .map(CommentParamsMapper::toCommentParams)
                .toList();
    }
    public static CommentParams fromEntityToCommentParams(Comment comment) {
        return CommentParams.builder()
                .content(comment.getContent())
                .userParams(UserParamsMapper.fromEntityToUserParams(comment.getUser()))
                .build();
    }

    public static List<CommentParams> fromEntityToCommentParamList(List<Comment> commentList) {
        return commentList.stream()
                .map(CommentParamsMapper::fromEntityToCommentParams)
                .toList();
    }


}
