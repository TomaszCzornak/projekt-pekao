package com.pekao.projektpekao.infrastructure;

import com.pekao.projektpekao.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BookDao {
    List<Book> findAllBooks();
    Optional<Book> findById(Long id);
    Optional<Book> findByTitle(String title);
    void deleteById(Long id);
    Book addBook(Book book);
    Book updateBook(Book book);

}
