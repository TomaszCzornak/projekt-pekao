package com.pekao.projektpekao.service;

import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.exception.NotFoundException;
import com.pekao.projektpekao.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    public Book findBookById(Long id) {
        return findOrThrow(id);
    }
    private Book findOrThrow(Long id) {
        return bookRepository.findById(id).orElseThrow(
                        () -> new NotFoundException("Nie ma książki o id " + id ));
    }
    public void removeBookById(Long id) {
        bookRepository.deleteById(id);
    }
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    public void updateBook(Long id,Book book) {
        findOrThrow(id);
        bookRepository.save(book);
    }
}
