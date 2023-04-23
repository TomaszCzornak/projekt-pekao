package com.pekao.projektpekao.controller;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorResponse {

    private AuthorWithBooksDto authorResponse;
}
