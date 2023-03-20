package com.pekao.projektpekao.service;

import com.pekao.projektpekao.entity.Author;
import com.pekao.projektpekao.infrastructure.AuthorDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class AuthorService {

    @Resource(name = "AuthorDaoJpaImpl")
    private final AuthorDao authorDaoJpa;

    public AuthorService(@Qualifier("AuthorDaoJpaImpl") AuthorDao authorDaoJpa) {
        this.authorDaoJpa = authorDaoJpa;
    }

    public List findAllAuthors() {
        return authorDaoJpa.findAll();
    }

    public Author findAuthorById(Long id) {
        return authorDaoJpa.findById(id);
    }

    public void removeAuthorById(Long id) {
        authorDaoJpa.deleteAuthorById(id);
    }

    public Author addAuthor(Author author) {
        return authorDaoJpa.addAuthor(author);
    }

    public Author updateAuthor(Long id, Author author) {
        Author author1 = authorDaoJpa.findById(id);
        return authorDaoJpa.addAuthor(author1);
    }
}
