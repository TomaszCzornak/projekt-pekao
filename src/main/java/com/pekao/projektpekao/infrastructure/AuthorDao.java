package com.pekao.projektpekao.infrastructure;

import com.pekao.projektpekao.domain.Author.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    List<Author>  findAll();
    Optional<Author>  findById(Long id);

    Author addAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);

    Author findByLastName(String lastName);

}
