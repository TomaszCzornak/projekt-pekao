package com.pekao.projektpekao.infrastructure;

import com.pekao.projektpekao.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    void deleteById(Long id);
    Book addBook(Book book);
    Book updateBook(Book book);

}
