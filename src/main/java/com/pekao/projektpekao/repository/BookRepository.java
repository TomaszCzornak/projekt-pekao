package com.pekao.projektpekao.repository;

import com.pekao.projektpekao.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);
    @Query("SELECT b FROM Book b join fetch b.author a JOIN FETCH b.commentList cl  join fetch b.electronicJournal ej")
//    @Query(value = "select * from Book join Comment on Book.ID = Comment.Book_id join Author on Book.Author_id = Author.id", nativeQuery = true)
    List<Book> findAllBooks();
}
