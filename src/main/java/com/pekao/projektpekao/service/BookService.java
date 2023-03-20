package com.pekao.projektpekao.service;

import com.pekao.projektpekao.exception.NotFoundException;
import com.pekao.projektpekao.infrastructure.BookDao;
import com.pekao.projektpekao.entity.Book;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Resource(name = "BookDaoJpaImpl")
    private final BookDao bookDaoJpa;

    public BookService( BookDao bookDaoJpa) {
        this.bookDaoJpa = bookDaoJpa;
    }
    public List<Book> findAllBooks() {
        return bookDaoJpa.findAll();
    }
    public Optional<Book> findBookById(Long id) {
        return bookDaoJpa.findById(id);
    }
    private Book findOrThrow(Long id) {
        return bookDaoJpa.findById(id).orElseThrow(
                        () -> new NotFoundException("Nie ma książki o id " + id ));
    }
    public void removeBookById(Long id) {
        bookDaoJpa.deleteById(id);
    }
    public Book addBook(Book book) {
        return bookDaoJpa.addBook(book);
    }
    public void updateBook(Long id,Book book) {
        findOrThrow(id);
        bookDaoJpa.addBook(book);
    }
}
