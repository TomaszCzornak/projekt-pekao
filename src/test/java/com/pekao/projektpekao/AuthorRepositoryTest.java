package com.pekao.projektpekao;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pekao.projektpekao.entity.Author;
import com.pekao.projektpekao.repository.AuthorRepository;


@DataJpaTest
public class AuthorRepositoryTest {


    private final AuthorRepository authorRepository;

    public AuthorRepositoryTest(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Test
    void saveAuthor() {
        authorRepository.save(new Author("Lucy", "Smith"));
        assertThat(authorRepository.findByFirstName("Lucy").isPresent()).isTrue();
    }
    @Test
    void deleteAuthors() {
        authorRepository.save(new Author("Lisa", "Morrison"));
        authorRepository.deleteAll();
        assertThat(authorRepository.count()).isEqualTo(0);
    }

}
