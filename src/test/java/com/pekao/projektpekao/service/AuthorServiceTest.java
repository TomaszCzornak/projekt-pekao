package com.pekao.projektpekao.service;

import com.pekao.projektpekao.AuthorTestUtility;
import com.pekao.projektpekao.domain.Author.AuthorParams;
import com.pekao.projektpekao.domain.Author.Author;
import com.pekao.projektpekao.domain.Author.AuthorParamsMapper;
import com.pekao.projektpekao.repository.*;
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
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ElectronicJournalRepository electronicJournalRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        commentRepository.deleteAll();
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        electronicJournalRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findAllAuthors() {
        //given
        final List<Author> authorsToSave = List.of(AuthorTestUtility.createAuthorEntity("Stephen","King"),
        AuthorTestUtility.createAuthorEntity("Marco", "Polo"),AuthorTestUtility.createAuthorEntity("Rysiek","Kapuściński"));
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
        Author authorToSave = AuthorTestUtility.createAuthorEntity("Mary","Jane");
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
        Author authorToSave = AuthorTestUtility.createAuthorEntity("Tomasz","Bykowski");
        Author authorSaved = authorRepository.save(authorToSave);
        //when
        authorService.removeAuthorById(authorSaved.getId());
        //then
        assertThrows(IllegalStateException.class, ()-> authorService.findAuthorById(authorSaved.getId()));
    }

    @Test
    void addAuthor() {
        //given
        AuthorParams authorToSave = AuthorTestUtility.createAuthor("Pierre","Cardin");
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
        Author authorSaved = authorService.addAuthor(AuthorTestUtility.createAuthor("Johnny", "Walker"));
        AuthorParams authorParamsMapped = AuthorParamsMapper.fromEntityToAuthorParams(authorSaved);
        AuthorParams authorChangedToSave = authorParamsMapped.toBuilder()
                .lastName("B. Good")
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
        Author authorSaved = authorService.addAuthor(AuthorTestUtility.createAuthor("Johnny", "Walker"));
        //when
        Author authorFound = authorService.findAuthorByLastName(authorSaved.getLastName());
        //then
        assertEquals(authorFound.getLastName(), authorSaved.getLastName());
    }
}
