package com.pekao.projektpekao.controller.Author;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AuthorsResponse {

    private List<AuthorDto> authorResponseList;
}
