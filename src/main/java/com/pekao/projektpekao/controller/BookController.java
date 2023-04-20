package com.pekao.projektpekao.controller;

import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.entity.BookDto;
import com.pekao.projektpekao.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/all")
    public BookResponse getAllBooks() {
        LOGGER.info("Printing all books");
        List<BookDto> bookDtoList = BookDtoMapper.bookDtos(bookService.findAllBooks());
        return BookResponse.builder()
                .bookResponseList(bookDtoList)
                .build();
    }
    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable("id") Long id) {
        BookDto singleBook = BookDtoMapper.toBookDto(bookService.findBookById(id));
        return BookResponse.builder()
                .singleBookDto(singleBook)
                .build();
    }
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        bookService.removeBookById(id);
    }

    @PostMapping()
    public Book postBook(@RequestBody Book book) {
       return bookService.addBook(book);
    }
    @PutMapping("/{id}")
    public void putBook(@PathVariable("id") Long id, @RequestBody Book book) {
        if(!Objects.equals(id, bookService.findBookById(id).getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id does not match");
        }
        bookService.updateBook(book);
    }
}
