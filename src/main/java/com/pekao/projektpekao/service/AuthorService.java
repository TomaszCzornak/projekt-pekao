package com.pekao.projektpekao.service;

import com.pekao.projektpekao.entity.Author;
import com.pekao.projektpekao.exception.NotFoundException;
import com.pekao.projektpekao.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List findAllAuthors() {
        return authorRepository.findAll();
    }
    public Author findAuthorById(Long id) {
        return findOrThrow(id);
    }
    private Author findOrThrow(Long id) {
        return authorRepository
                .findById(id)
                .orElseThrow(
                () -> new NotFoundException("This author by id " + id + " was not found"));
    }
    public void removeAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }
    public Author updateAuthor(Long id, Author author) {
        findOrThrow(id);
        return authorRepository.save(author);
    }
}
