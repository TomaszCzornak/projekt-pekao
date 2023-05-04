package com.pekao.projektpekao.repository;

import com.pekao.projektpekao.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> getBookByTitle(String title);
    @Query("SELECT b FROM Book b")
    List<Book> findAllBooks();
    Optional<Book> getBookById(Long id);
}
