package com.pekao.projektpekao.controller;

import com.pekao.projektpekao.entity.BookDto;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
@Builder
@Getter
@Jacksonized
public class BookResponse {

    private List<BookDto> bookResponseList;
    private BookDto singleBookDto;
}
