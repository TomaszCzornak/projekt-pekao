package com.pekao.projektpekao.service;

import com.pekao.projektpekao.AuthorTestUtility;
import com.pekao.projektpekao.domain.Author;
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
        final List<Author> authorsToSave = List.of(AuthorTestUtility.createAuthor("Stephen","King"),
        AuthorTestUtility.createAuthor("Marco", "Polo"),AuthorTestUtility.createAuthor("Rysiek","Kapuściński"));
        List<Author> authorsSaved = authorRepository.saveAll(authorsToSave);
        //when
        List <Author> authorsFound = authorService.findAllAuthors();
        //then
        assertThat(authorsFound, hasSize(3));
        assertThat(
                authorsFound.stream().anyMatch(n -> n.getLastName().equals(authorsSaved.get(0).getLastName()))
        ).isTrue();
        assertThat(
                authorsFound.stream().anyMatch(n -> n.getLastName().equals(authorsSaved.get(1).getLastName()))
        ).isTrue();

    }

    @Test
    void findAuthorById() {
        //given
        Author authorToSave = AuthorTestUtility.createAuthor("Mary","Jane");
        Author savedAuthor = authorRepository.save(authorToSave);
        //when
        Author authorFound = authorService.findAuthorById(authorToSave.getId());
        //then
        assertThat(authorFound).extracting(Author::getId, Author::getFirstName, Author::getLastName)
                .doesNotContainNull()
                .containsExactly(savedAuthor.getId(),"Mary", "Jane");

    }

    @Test
    void removeAuthorById() {
        //given
        Author authorToSave = AuthorTestUtility.createAuthor("Tomasz","Bykowski");
        Author authorSaved = authorRepository.save(authorToSave);
        //when
        authorService.removeAuthorById(authorSaved.getId());
        //then
        assertThrows(IllegalStateException.class, ()-> authorService.findAuthorById(authorSaved.getId()));
    }

    @Test
    void addAuthor() {
        //given
        Author authorToSave = AuthorTestUtility.createAuthor("Pierre","Cardin");
        //when
        Author authorSaved = authorService.addAuthor(authorToSave);
        //then
        assertThat(authorSaved).extracting("firstName", "lastName")
                .doesNotContainNull()
                .containsExactly("Pierre","Cardin");
    }

    @Test
    void updateAuthor() {
        //given
        Author authorSaved = authorRepository.save(AuthorTestUtility.createAuthor("Johnny", "Walker"));
        Author authorChangedToSave = Author.builder()
                .from(authorSaved)
                .withLastName("B. Good")
                .build();
        //when
        authorService.updateAuthor(authorChangedToSave);
        Author changedAuthorFound = authorService.findAuthorByLastName(authorChangedToSave.getLastName());
        //then
        assertEquals(changedAuthorFound.getLastName(), authorChangedToSave.getLastName());
    }
    @Test
    void findAuthorByLastName() {
        //given
        Author authorSaved = authorRepository.save(AuthorTestUtility.createAuthor("Johnny", "Walker"));
        //when
        Author authorFound = authorService.findAuthorByLastName(authorSaved.getLastName());
        //then
        assertEquals(authorFound.getLastName(), authorSaved.getLastName());
    }
}
