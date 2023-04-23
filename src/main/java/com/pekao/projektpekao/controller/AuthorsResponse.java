package com.pekao.projektpekao.controller;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AuthorsResponse {

    private List<AuthorDto> authorResponseList;
}
