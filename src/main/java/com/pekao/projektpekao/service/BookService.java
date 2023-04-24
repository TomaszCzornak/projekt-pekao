package com.pekao.projektpekao.service;

import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.infrastructure.BookDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {
    @Resource(name = "BookDaoJpaImpl")
    private final BookDao bookDaoJpa;

    public BookService(@Qualifier("BookDaoJpaImpl") BookDao bookDaoJpa) {
        this.bookDaoJpa = bookDaoJpa;
    }

    public List<Book> findAllBooks() {
        return bookDaoJpa.findAllBooks();
    }

    public Book findBookById(Long id) {
        return bookDaoJpa.findById(id).orElseThrow(()->new IllegalStateException("Cannot find book with id: " + id));
    }

    public Book findBookByTitle(String title) {
        return bookDaoJpa.findByTitle(title).orElseThrow(()->new IllegalStateException("Cannot find book with title "+ title));
    }

    public void removeBookById(Long id) {
        bookDaoJpa.deleteById(id);
    }

    public Book addBook(Book book) {
        return bookDaoJpa.addBook(book);
    }

    public Book updateBook(Book book) {
       return bookDaoJpa.addBook(book);
    }
}
