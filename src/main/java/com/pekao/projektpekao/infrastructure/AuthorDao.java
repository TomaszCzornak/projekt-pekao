package com.pekao.projektpekao.infrastructure;

import com.pekao.projektpekao.entity.Author;

import java.util.List;

public interface AuthorDao {
    List<Author>  findAll();
    Author  findById(Long id);

    Author addAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);

}
