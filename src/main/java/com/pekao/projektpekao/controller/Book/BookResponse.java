package com.pekao.projektpekao.controller.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Jacksonized
public class BookResponse {

    private BookDto bookDtoResponse;
}
