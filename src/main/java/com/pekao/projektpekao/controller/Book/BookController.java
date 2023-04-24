package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.entity.Book;
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
    public BooksResponse getAllBooks() {
        LOGGER.info("Printing all books");
        List<BookDto> bookDtoList = BookDtoMapper.toBookDtos(bookService.findAllBooks());
        return BooksResponse.builder()
                .bookResponseList(bookDtoList)
                .build();
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable("id") Long id) {
        BookDto singleBook = BookDtoMapper.toBookDto(bookService.findBookById(id));
        return BookResponse.builder()
                .bookDtoResponse(singleBook)
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        bookService.removeBookById(id);
    }

    @PostMapping()
    public BookResponse postBook(@RequestBody BookDto bookDto) {
        Book bookToPost = BookEntityMapper.toBookEntity(bookDto);
        Book bookSaved = bookService.addBook(bookToPost);
        BookDto bookDto1 = BookDtoMapper.toBookDto(bookSaved);
        return BookResponse.builder()
                .bookDtoResponse(bookDto1)
                .build();
    }

    @PutMapping("/{id}")
    public void putBook(@PathVariable("id") Long id, @RequestBody BookDto bookDto) {
        if (!Objects.equals(id, bookService.findBookById(id).getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id does not match");
        }
        Book bookToPut = BookEntityMapper.toBookEntity(bookDto);
        bookService.updateBook(bookToPut);
    }
}
