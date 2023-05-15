package com.pekao.projektpekao.service;

import com.pekao.projektpekao.AuthorTestUtility;
import com.pekao.projektpekao.BookTestUtility;
import com.pekao.projektpekao.domain.Author.AuthorParams;
import com.pekao.projektpekao.domain.Author.AuthorParamsMapper;
import com.pekao.projektpekao.domain.Book.BookParams;
import com.pekao.projektpekao.domain.Author.Author;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal;
import com.pekao.projektpekao.domain.Book.Book;
import com.pekao.projektpekao.domain.Book.BookParamsMapper;
import com.pekao.projektpekao.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@TestPropertySource("/application-test.properties") //alternative in case of test issues
class BookServiceTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ElectronicJournalRepository electronicJournalRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;


    @AfterEach
    void tearDown() {
        commentRepository.deleteAll();
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        electronicJournalRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void shouldCreateBookOfEventTypeWhenPublisherWYDAWNICTWO_LITERACKIE() {
        //createBookWithTitleAuthorAndPublisherAndCommentsAndUser
        //given
        Author authorSaved = authorRepository.save(AuthorTestUtility.createAuthorEntity("Mary", "Smith"));
        AuthorParams authorParams1 = AuthorParamsMapper.fromEntityToAuthorParams(authorSaved);
        BookParams bookParams = BookTestUtility.createBookWithTitleAuthorAndPublisherAndCommentsAndUser("Książka", Book.Publisher.WYDAWNICTWO_LITERACKIE, authorParams1);
        //when
        bookService.addBook(bookParams);
        ElectronicJournal electronicJournalFound = electronicJournalRepository.findElectronicJournalByName("Tu wydawcą jest Wydawnictwo Literackie");
        //then
        assertEquals(ElectronicJournal.EventType.MANAGER, electronicJournalFound.getEventType());
    }


    @Test
    void shouldCreateBookOfEventTypeWhenPublisherPWN() {
        //given
        Author authorSaved = authorRepository.save(AuthorTestUtility.createAuthorEntity("Stephen", "King"));
        AuthorParams authorParams1 = AuthorParamsMapper.fromEntityToAuthorParams(authorSaved);
        BookParams bookParams = BookTestUtility.createBookWithTitleAuthorAndPublisherAndCommentsAndUser("Książka", Book.Publisher.PWN, authorParams1);
        //when
        bookService.addBook(bookParams);
        ElectronicJournal electronicJournalFound = electronicJournalRepository.findElectronicJournalByName("Tu wydawcą jest PWN");
        //then
        assertEquals(ElectronicJournal.EventType.DONE, electronicJournalFound.getEventType());
    }

    @Test
    void shouldCreateBookOfEventTypeWhenPublisherZNAK() {
        //given
        Author authorSaved = authorRepository.save(AuthorTestUtility.createAuthorEntity("Stephen", "King"));
        AuthorParams authorParams1 = AuthorParamsMapper.fromEntityToAuthorParams(authorSaved);
        BookParams bookParams = BookTestUtility.createBookWithTitleAuthorAndPublisherAndCommentsAndUser("Książka", Book.Publisher.ZNAK, authorParams1);
        //when
        bookService.addBook(bookParams);
        ElectronicJournal electronicJournalFound = electronicJournalRepository.findElectronicJournalByName("Tu wydawcą jest ZNAK");
        //then
        assertEquals(ElectronicJournal.EventType.TO_DO, electronicJournalFound.getEventType());
    }

    @Test
    void shouldCreateBookOfEventTypeWhenPublisherAGORA() {
        //given
        Author authorSaved = authorRepository.save(AuthorTestUtility.createAuthorEntity("Stephen", "King"));
        AuthorParams authorParams1 = AuthorParamsMapper.fromEntityToAuthorParams(authorSaved);
        BookParams bookParams = BookTestUtility.createBookWithTitleAuthorAndPublisherAndCommentsAndUser("Książka", Book.Publisher.AGORA, authorParams1);
        //when
        bookService.addBook(bookParams);
        ElectronicJournal electronicJournalFound = electronicJournalRepository.findElectronicJournalByName("Tu wydawcą jest AGORA");
        //then
        assertEquals(ElectronicJournal.EventType.WIP, electronicJournalFound.getEventType());
    }


    @Test
    void findAllBooks() {
        //given
        Author authorSaved1 = authorRepository.save(AuthorTestUtility.createdAuthorWithFirstNameAndLastName("Jan", "Kowalski"));
        Author authorSaved2 = authorRepository.save(AuthorTestUtility.createdAuthorWithFirstNameAndLastName("Marian", "Mariańśki"));
        List<Book> listOfBooks = List.of(BookTestUtility.createBookAllParameters(authorSaved1), BookTestUtility.createBookAllParameters(authorSaved2));
        List<Book> booksSaved = bookRepository.saveAll((listOfBooks));
        //when
        List<Book> allBooksFound = bookService.findAllBooks();
        //then
        assertThat(allBooksFound, hasSize(2));
        Assertions.assertThat(
                allBooksFound.stream().anyMatch(n -> n.getPublisher().equals(booksSaved.get(0).getPublisher()))
        ).isTrue();
        Assertions.assertThat(
                allBooksFound.stream().anyMatch(n -> n.getPublisher().equals(booksSaved.get(1).getPublisher()))
        ).isTrue();
    }

    @Test
    void findBookById() {
        //given
        Author authorSaved1 = authorRepository.save(AuthorTestUtility.createdAuthorWithFirstNameAndLastName("Jan", "Kowalski"));
        Book bookSaved = BookTestUtility.createBookAllParameters(authorSaved1);
        bookRepository.save(bookSaved);
        //when
        Book bookFound = bookService.findBookById(bookSaved.getId());
        //then
        assertEquals(bookSaved.getId(), bookFound.getId());
    }

    @Test
    void findBookByTitle() {
        //given
        Author authorSaved1 = authorRepository.save(AuthorTestUtility.createdAuthorWithFirstNameAndLastName("Jan", "Kowalski"));
        Book bookSaved = BookTestUtility.createBookAllParameters(authorSaved1);
        bookRepository.save(bookSaved);
        //when
        Book foundBook = bookService.findBookByTitle(bookSaved.getTitle());
        //then
        assertEquals(foundBook.getTitle(), bookSaved.getTitle());
    }

    @Test
    void removeBookById() {
        //given
        Author authorSaved1 = authorRepository.save(AuthorTestUtility.createdAuthorWithFirstNameAndLastName("Jan", "Kowalski"));
        Book bookSaved = BookTestUtility.createBookAllParameters(authorSaved1);
        bookRepository.save(bookSaved);
        //when
        bookService.removeBookById(bookSaved.getId());
        //then
        assertThrows(IllegalStateException.class, () -> bookService.findBookById(bookSaved.getId()));
    }

    @Test
    void addBook() {
        //given
        Author authorSaved = authorRepository.save(AuthorTestUtility.createAuthorEntity("Stephen", "King"));
        AuthorParams authorParams1 = AuthorParamsMapper.fromEntityToAuthorParams(authorSaved);
        BookParams bookParams = BookTestUtility.createBookWithTitleAuthorAndPublisherAndCommentsAndUser("Książka", Book.Publisher.AGORA, authorParams1);
        //when
        Book bookFound = bookService.addBook(bookParams);
        //then
        assertNotNull(bookFound);

    }

    @Test
    void updateBook() {
        //given
        Author authorSaved = authorRepository.save(AuthorTestUtility.createAuthorEntity("Stephen", "King"));
        AuthorParams authorParams1 = AuthorParamsMapper.fromEntityToAuthorParams(authorSaved);
        BookParams bookParams = BookTestUtility.createBookWithTitleAuthorAndPublisherAndCommentsAndUser("Książka", Book.Publisher.AGORA, authorParams1);
        //when
        Book bookFound = bookService.addBook(bookParams);
        BookParams bookParams1 = BookParamsMapper.fromEntityToParams(bookFound);
        BookParams bookWithChangedData = bookParams1.toBuilder()
                .title("This title has been changed now")
                .build();

        //when
        Book bookFoundWithChangedDataFound = bookService.updateBook(bookWithChangedData);
        //then
        assertEquals(bookWithChangedData.getTitle(), bookFoundWithChangedDataFound.getTitle());
    }
}
