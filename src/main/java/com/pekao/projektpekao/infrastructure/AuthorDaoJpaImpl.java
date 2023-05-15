package com.pekao.projektpekao.infrastructure;

import com.pekao.projektpekao.domain.Author.Author;
import com.pekao.projektpekao.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("AuthorDaoJpaImpl")
public class AuthorDaoJpaImpl implements AuthorDao {

    private final AuthorRepository authorRepository;

    AuthorDaoJpaImpl(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(final Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author findByLastName(String lastName) {
        return authorRepository.findAuthorByLastName(lastName);
    }
}
