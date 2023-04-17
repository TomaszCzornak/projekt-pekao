package com.pekao.projektpekao.service;

import com.pekao.projektpekao.AuthorTestUtility;
import com.pekao.projektpekao.BookTestUtility;
import com.pekao.projektpekao.entity.Author;
import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.entity.ElectronicJournal;
import com.pekao.projektpekao.repository.BookRepository;
import com.pekao.projektpekao.repository.CommentRepository;
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
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;


    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    void shouldCreateBookOfEventTypeWhenPublisherWYDAWNICTWO_LITERACKIE() {
        //given
        Book book = BookTestUtility.createBookWithPublisher(Book.Publisher.WYDAWNICTWO_LITERACKIE);
        //when
        Book bookFound = bookService.addBook(book);
        //then
        assertEquals(bookFound.getElectronicJournal().getEventType(), ElectronicJournal.EventType.MANAGER);

    }

    @Test
    void shouldCreateBookOfEventTypeWhenPublisherPWN() {
        //given
        Book book = BookTestUtility.createBookWithPublisher(Book.Publisher.PWN);
        //when
        Book bookFound = bookService.addBook(book);
        //then
        assertEquals(bookFound.getElectronicJournal().getEventType(), ElectronicJournal.EventType.DONE);
    }

    @Test
    void shouldCreateBookOfEventTypeWhenPublisherZNAK() {
        //given
        Book book = BookTestUtility.createBookWithPublisher(Book.Publisher.ZNAK);
        //when
        Book bookFound = bookService.addBook(book);
        //then
        assertEquals(bookFound.getElectronicJournal().getEventType(), ElectronicJournal.EventType.TO_DO);
    }

    @Test
    void shouldCreateBookOfEventTypeWhenPublisherAGORA() {
        //given
        Book book = BookTestUtility.createBookWithPublisher(Book.Publisher.AGORA);
        //when
        Book bookFound = bookService.addBook(book);
        //then
        assertEquals(bookFound.getElectronicJournal().getEventType(), ElectronicJournal.EventType.WIP);
    }


    @Test
    void findAllBooks() {
        //given
        final List<Book> booksToSave = List.of(BookTestUtility.createBookWithPublisher(Book.Publisher.ZNAK),
                BookTestUtility.createBookWithPublisher(Book.Publisher.PWN),
                BookTestUtility.createBookWithPublisher(Book.Publisher.AGORA),
                BookTestUtility.createBookWithPublisher(Book.Publisher.WYDAWNICTWO_LITERACKIE));
        bookRepository.saveAll((booksToSave));
        //when
        List<Book> allBooksFound = bookService.findAllBooks();
        //then
        assertThat(allBooksFound, hasSize(4));
        Assertions.assertThat(
                allBooksFound.stream().anyMatch(n -> n.getPublisher().equals(booksToSave.get(0).getPublisher()))
        ).isTrue();
        Assertions.assertThat(
                allBooksFound.stream().anyMatch(n -> n.getPublisher().equals(booksToSave.get(1).getPublisher()))
        ).isTrue();
        Assertions.assertThat(
                allBooksFound.stream().anyMatch(n -> n.getPublisher().equals(booksToSave.get(2).getPublisher()))
        ).isTrue();
        Assertions.assertThat(
                allBooksFound.stream().anyMatch(n -> n.getPublisher().equals(booksToSave.get(3).getPublisher()))
        ).isTrue();
    }

    @Test
    void findBookById() {
        //given
        Book bookSaved = bookRepository.save(BookTestUtility.createBookWithPublisher(Book.Publisher.ZNAK));
        //when
        Book bookFound = bookService.findBookById(bookSaved.getId());
        //then
        assertEquals(bookSaved.getId(), bookFound.getId());
    }

    @Test
    void findBookByTitle() {
        //given
        Author authorToSave = authorService.addAuthor(AuthorTestUtility.createAuthor("Mark", "Spencer"));
        Book bookWithTitleToSave = bookRepository.save(BookTestUtility.createBookWithTitleAndAuthor("What's the story morning glory", authorToSave));
        //when
        Book bookByTitle = bookService.findBookByTitle(bookWithTitleToSave.getTitle());
        //then
        assertEquals(bookByTitle.getTitle(), "What's the story morning glory");
    }

    @Test
    void removeBookById() {
        //given
        Author authorToSave = authorService.addAuthor(AuthorTestUtility.createAuthor("Mark", "Spencer"));
        Book bookWithTitleSaved = bookRepository.save(BookTestUtility.createBookWithTitleAndAuthor("What's the story morning glory", authorToSave));
        //when
        bookService.removeBookById(bookWithTitleSaved.getId());
        //then
        assertThrows(IllegalStateException.class, () -> bookService.findBookById(bookWithTitleSaved.getId()));
    }

    @Test
    void addBook() {
        //given
        Author authorToSave = authorService.addAuthor(AuthorTestUtility.createAuthor("Mark", "Spencer"));
        Book bookToBeAdded = BookTestUtility.createBookWithTitleAndAuthor("What's the story morning glory", authorToSave);
        //when
        Book bookSaved = bookService.addBook((bookToBeAdded));
        //then
        assertNotNull(bookSaved);

    }

    @Test
    void updateBook() {
        //given
        Author authorToSave = authorService.addAuthor(AuthorTestUtility.createAuthor("Mark", "Spencer"));
        Book bookWithTitleSaved = bookRepository.save(BookTestUtility.createBookWithTitleAndAuthor("Exemplary Title",authorToSave));
        Book bookWithChangedData = Book.builder()
                .fromExisting(bookWithTitleSaved)
                .title("This title has been changed now")
                .build();
        //when
        Book bookFoundWithChangedData = bookService.updateBook(bookWithChangedData);
        //then
        assertEquals(bookFoundWithChangedData.getTitle(), "This title has been changed now");
    }
}