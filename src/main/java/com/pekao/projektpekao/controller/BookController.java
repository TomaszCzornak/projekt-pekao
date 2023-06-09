package com.pekao.projektpekao.controller;

import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/all")
    public List<Book> getAllBooks() {
        LOGGER.info("Printing all books");
        return bookService.findAllBooks();
    }
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable("id") Long id) {
        return bookService.findBookById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        bookService.removeBookById(id);
    }

    @PostMapping()
    public BookDto postBook(@RequestBody Book book) {
       return bookService.addBook(book);
    }
    @PutMapping("/{id}")
    public void putBook(@PathVariable("id") Long id, @RequestBody Book book) {
        if(!id.equals(book.getId())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id does not match");
        bookService.updateBook(id, book);
    }
}
