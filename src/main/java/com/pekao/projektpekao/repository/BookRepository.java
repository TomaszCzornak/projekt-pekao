package com.pekao.projektpekao.repository;

import com.pekao.projektpekao.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {

}
