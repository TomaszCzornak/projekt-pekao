package com.pekao.projektpekao.infrastructure;

import com.pekao.projektpekao.entity.Author;
import com.pekao.projektpekao.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public Author findById(final Long id) {
        return authorRepository.findById(id).orElseThrow();
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
}
