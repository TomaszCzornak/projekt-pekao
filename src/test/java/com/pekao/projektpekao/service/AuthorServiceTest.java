package com.pekao.projektpekao.service;

import com.pekao.projektpekao.entity.Author;
import com.pekao.projektpekao.repository.AuthorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
//@TestPropertySource("/application-test.properties") //alternative in case of test issues
class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        authorRepository.deleteAll();
    }

    @Test
    void findAllAuthors() {
        //given
        Author author1 = new Author("Pierre-Yves_testowy", "Saumont");
        Author author2 = new Author("John_testowy", "Walker");
        Author author3 = new Author("Stephen_testowy", "King");
        authorRepository.saveAll(List.of(author1, author2, author3));
        //when
        List<Author> authorList = authorService.findAllAuthors();
        //then
        assertThat(authorList, hasSize(3));

    }

    @Test
    void findAuthorById() {
        //given
        Author authorToSave = new Author("Pierre-Yves", "Saumont");
        Author savedAuthor = authorRepository.save(authorToSave);
        //when
        Author authorFound = authorService.findAuthorById(authorToSave.getId());
        //then
        assertThat(authorFound).extracting("id","firstName", "lastName")
                .doesNotContainNull()
                .containsExactly(savedAuthor.getId(),"Pierre-Yves", "Saumont");

    }

    @Test
    void removeAuthorById() {
        //given
        Author authorToSave = new Author("Pierre-Yves", "Saumont");
        Author authorSaved = authorRepository.save(authorToSave);
        //when
        authorService.removeAuthorById(authorSaved.getId());
        //then
        assertThrows(IllegalStateException.class, ()-> authorService.findAuthorById(authorSaved.getId()));
    }

    @Test
    void addAuthor() {
        //given
        Author authorToSave = new Author("Pierre-Yves", "Saumont");
        //when
        Author authorSaved = authorService.addAuthor(authorToSave);
        //then
        assertThat(authorSaved).extracting("firstName", "lastName")
                .doesNotContainNull()
                .containsExactly("Pierre-Yves", "Saumont");
    }

    @Test
    void updateAuthor() {
        //given
        Author authorToSave = new Author("John", "Walker");
        authorRepository.save(authorToSave);
        Author authorByLastNameFound = authorService.findAuthorByLastName(authorToSave.getLastName());
        //when
        authorByLastNameFound.setLastName("Borrows");
        authorService.updateAuthor(authorByLastNameFound.getId(), authorByLastNameFound);
        Author changedAuthor = authorService.findAuthorByLastName(authorByLastNameFound.getLastName());
        //then
        assertEquals(changedAuthor.getLastName(), authorByLastNameFound.getLastName());
    }
    @Test
    void findAuthorByLastName() {
        //given
        Author authorToSave = new Author("Pierre-Yves", "Saumont");
        Author authorSaved = authorRepository.save(authorToSave);
        //when
        Author authorFound = authorService.findAuthorByLastName(authorSaved.getLastName());
        //then
        assertEquals(authorFound.getLastName(), authorSaved.getLastName());
    }
}