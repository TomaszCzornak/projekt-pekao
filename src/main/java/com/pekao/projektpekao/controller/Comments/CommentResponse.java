package com.pekao.projektpekao.controller.Comments;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CommentResponse {
    private CommentDto commentResponse;
}
