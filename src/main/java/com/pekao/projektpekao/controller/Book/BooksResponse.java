package com.pekao.projektpekao.controller.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
@Builder
@Getter
@Jacksonized
public class BooksResponse {

    private List<BookDto> bookResponseList;
}
