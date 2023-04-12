package com.pekao.projektpekao.service;

import com.pekao.projektpekao.entity.Author;
import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.entity.Comment;
import com.pekao.projektpekao.entity.ElectronicJournal;
import com.pekao.projektpekao.repository.BookRepository;
import com.pekao.projektpekao.repository.CommentRepository;
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
        Book.Publisher publisher = Book.Publisher.WYDAWNICTWO_LITERACKIE;
        //when
        Author author = authorService.addAuthor(new Author("Stephen", "King"));
        Comment comment1 = commentRepository.save(new Comment("Dupa Jasiu"));

        Book book = new Book("Wydawnicto Literackie", author, List.of(comment1),publisher);
        Book bookFound = bookService.addBook(book);
        //then
        assertEquals(bookFound.getElectronicJournal().getEventType(), ElectronicJournal.EventType.MANAGER);
    }
    @Test
    void shouldCreateBookOfEventTypeWhenPublisherPWN() {
        //given
        Book.Publisher publisher = Book.Publisher.PWN;
        //when
        Author author = authorService.addAuthor(new Author("Stephen", "King"));
        Comment comment1 = commentRepository.save(new Comment("Dupa Jasiu"));

        Book book = new Book("Pwn", author, List.of(comment1),publisher);
        Book bookSaved = bookService.addBook(book);
        //then
        assertEquals(bookSaved.getElectronicJournal().getEventType(), ElectronicJournal.EventType.DONE);
    }

    @Test
    void shouldCreateBookOfEventTypeWhenPublisherZNAK() {
        //given
        Book.Publisher publisher = Book.Publisher.ZNAK;
        //when
        Author author = authorService.addAuthor(new Author("Stephen", "King"));
        Comment comment1 = commentRepository.save(new Comment("Dupa Jasiu"));

        Book book = new Book("Znak", author, List.of(comment1),publisher);
        Book bookSaved = bookService.addBook(book);
        //then
        assertEquals(bookSaved.getElectronicJournal().getEventType(), ElectronicJournal.EventType.TO_DO);
    }
    @Test
    void shouldCreateBookOfEventTypeWhenPublisherAGORA() {
        //given
        Book.Publisher publisher = Book.Publisher.AGORA;
        //when
        Author author = authorService.addAuthor(new Author("Stephen", "King"));
        Comment comment1 = commentRepository.save(new Comment("Dupa Jasiu"));

        Book book = new Book("Agora", author, List.of(comment1),publisher);
        Book bookSaved = bookService.addBook(book);
        //then
        assertEquals(bookSaved.getElectronicJournal().getEventType(), ElectronicJournal.EventType.WIP);
    }


    @Test
    void findAllBooks() {
        //given
        Book.Publisher publisher = Book.Publisher.AGORA;
        Author author = authorService.addAuthor(new Author("Stephen", "King"));
        Comment comment1 = commentRepository.save(new Comment("Pierwszy komentarz"));
        Comment comment2 = commentRepository.save(new Comment("Drugi komentarz"));
        Comment comment3 = commentRepository.save(new Comment("Trzeci komentarz"));
        Book book1 = new Book("Find All books", author, List.of(comment1),publisher);
        Book book2 = new Book("Drugi tytuł", author, List.of(comment2),publisher);
        Book book3 = new Book("Trzeci tytuł", author, List.of(comment3),publisher);
        bookRepository.saveAll(List.of(book1,book2,book3));
        //when
        List<Book> allBooksFound = bookService.findAllBooks();
        //then
        assertThat(allBooksFound,hasSize(3));
    }

    @Test
    void findBookById() {
        //given
        Book.Publisher publisher = Book.Publisher.AGORA;
        Author author = authorService.addAuthor(new Author("Stephen", "King"));
        Comment comment1 = commentRepository.save(new Comment("Pierwszy komentarz"));
        Book book1 = new Book("Find All books", author, List.of(comment1),publisher);
        Book bookSaved = bookRepository.save(book1);
        //when
         Book book = bookService.findBookById(bookSaved.getId());

        //then
        assertEquals(book.getId(),bookSaved.getId());
    }

    @Test
    void findBookByTitle() {
        //given
        Book.Publisher publisher = Book.Publisher.AGORA;
        Author author = authorService.addAuthor(new Author("Stephen", "King"));
        Comment comment1 = commentRepository.save(new Comment("Pierwszy komentarz"));
        Comment comment2 = commentRepository.save(new Comment("Drugi komentarz"));
        Comment comment3 = commentRepository.save(new Comment("Trzeci komentarz"));
        Book book1 = new Book("Find All Books", author, List.of(comment1),publisher);
        Book book2 = new Book("Drugi tytuł", author, List.of(comment2),publisher);
        Book book3 = new Book("Trzeci tytuł", author, List.of(comment3),publisher);
        bookRepository.saveAll(List.of(book1,book2,book3));
        //when
        Book bookByTitle = bookService.findBookByTitle("Find All Books");
        //then
        assertEquals(bookByTitle.getTitle(), "Find All Books");
    }

    @Test
    void removeBookById() {
        //given
        Book.Publisher publisher = Book.Publisher.AGORA;
        Author author = authorService.addAuthor(new Author("Stephen", "King"));
        Comment comment1 = commentRepository.save(new Comment("Pierwszy komentarz"));
        Book book1 = new Book("Find All books", author, List.of(comment1),publisher);
        Book bookSaved = bookRepository.save(book1);
        //when
        bookService.removeBookById(bookSaved.getId());
        //then
        assertThrows(IllegalStateException.class, ()->bookService.findBookById(bookSaved.getId()));
    }

    @Test
    void addBook() {
        //given
        Book.Publisher publisher = Book.Publisher.AGORA;
        Author author = authorService.addAuthor(new Author("Stephen", "King"));
        Comment comment1 = commentRepository.save(new Comment("Pierwszy komentarz"));

        Book book1 = new Book("Find All books", author, List.of(comment1),publisher);
        //when
        bookService.addBook((book1));
        Book bookSaved = bookService.findBookByTitle("Find All books");
        //then
        assertNotNull(bookSaved);

    }

    @Test
    void updateBook() {
        //given
        Book.Publisher publisher = Book.Publisher.AGORA;
        Author author = authorService.addAuthor(new Author("Stephen", "King"));
        Comment comment1 = commentRepository.save(new Comment("Pierwszy komentarz"));
        Book book1 = new Book("Find All books", author, List.of(comment1),publisher);
        bookRepository.saveAll(List.of(book1));
        Book bookSaved = bookService.findBookByTitle("Find All books");
        //when
        bookSaved.setTitle("Changed Title");
        bookService.updateBook(bookSaved);
        Book changedBook = bookService.findBookByTitle("Changed Title");
        //then
        assertEquals(changedBook.getTitle(),"Changed Title");
    }
}