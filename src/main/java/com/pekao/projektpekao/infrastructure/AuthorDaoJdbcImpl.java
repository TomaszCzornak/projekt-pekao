package com.pekao.projektpekao.infrastructure;


import com.pekao.projektpekao.entity.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("AuthorDaoJdbcImpl")
class AuthorDaoJdbcImpl implements AuthorDao {
    @Override
    public List<Author> findAll() {
        // super wydaje zapytanie napsiane przez Maćka
        return null;
    }

    @Override
    public Author findById(final Long id) {
        // super wydaje zapytanie napsiane przez Maćka
        return null;
    }

    @Override
    public Author addAuthor(Author author) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {
        deleteAuthorById(id);
    }
}
