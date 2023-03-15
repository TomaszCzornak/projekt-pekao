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
        // TODO: 3/14/2023 IMPL
        //        authorDaoJpa.delete(id);
    }

    public Author addAuthor(Author author) {
        // TODO: 3/14/2023 IMPL
//        return authorDaoJpa.sa(author);
        return null;
    }

    public Author updateAuthor(Long id, Author author) {
        // TODO: 3/14/2023 IMPL
//        return authorDaoJpa.save(author);
        return null;
    }
}
