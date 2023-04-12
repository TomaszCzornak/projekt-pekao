package com.pekao.projektpekao.service;

import com.pekao.projektpekao.entity.Author;
import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.entity.Comment;
import com.pekao.projektpekao.entity.ElectronicJournal;
import com.pekao.projektpekao.repository.AuthorRepository;
import com.pekao.projektpekao.repository.BookRepository;
import com.pekao.projektpekao.repository.CommentRepository;
import com.pekao.projektpekao.repository.ElectronicJournalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ElectronicJournalServiceTest {

    @Autowired
    private ElectronicJournalService electronicJournalService;
    @Autowired
    private ElectronicJournalRepository electronicJournalRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        electronicJournalRepository.deleteAll();
    }

    @Test
    void findAllElectronicJournals() {
        //given
        Author author2 = new Author("Dziennikowy Autor 1", "Test 1");
        Author author1 = new Author("Dziennikowy Autor 2", "Test 2");
        authorRepository.saveAll(List.of(author1, author2));
        Comment comment1 = commentRepository.save(new Comment("Komentarz książki testowy 1"));
        Comment comment2 = commentRepository.save(new Comment("Komentarz książki testowy 2"));
        Comment comment3 = commentRepository.save(new Comment("Komentarz książki testowy 3"));
        Book bookToSave1 = new Book("Testowy tytuł 1", author1, List.of(comment2), Book.Publisher.ZNAK);
        Book bookToSave2 = new Book("Testowy tytuł 2", author2, List.of(comment1), Book.Publisher.PWN);
        Book bookToSave3 = new Book("Testowy tytuł 3", author2, List.of(comment3), Book.Publisher.AGORA);
        bookRepository.saveAll(List.of(bookToSave1, bookToSave2, bookToSave3));
        //when
        List<ElectronicJournal> electronicJournalsFound = electronicJournalService.findAllElectronicJournals();
        //then
        assertThat(electronicJournalsFound, hasSize(3));
    }

    @Test
    void findElectronicJournalById() {
        //given
        Author author1 = new Author("Dziennikowy Autor 1", "Test 1");
        authorRepository.save(author1);
        Comment comment1 = commentRepository.save(new Comment("Komentarz książki testowy 1"));
        Book bookToSave = new Book("Testowy tytuł 1", author1, List.of(comment1), Book.Publisher.ZNAK);
        Book bookSaved = bookRepository.save(bookToSave);
        //when
        ElectronicJournal electronicJournalFound = electronicJournalService.findElectronicJournalById(bookSaved.getElectronicJournal().getId());
        //then
        assertEquals(bookSaved.getElectronicJournal().getId(), electronicJournalFound.getId());

    }

    @Test
    void removeElectronicJournalById() {
        //given
        ElectronicJournal electronicJournalToSave = new ElectronicJournal(ElectronicJournal.EventType.TO_DO);
        ElectronicJournal electronicJournalSaved = electronicJournalRepository.save(electronicJournalToSave);
        //when
        electronicJournalService.removeElectronicJournalById(electronicJournalSaved.getId());
        //then
        assertThrows(IllegalStateException.class, ()->electronicJournalService.findElectronicJournalById(electronicJournalSaved.getId()));

    }

    @Test
    void addElectronicJournal() {
        //given
        ElectronicJournal electronicJournalToSave = new ElectronicJournal(ElectronicJournal.EventType.MANAGER);
        //when
        ElectronicJournal electronicJournalSaved = electronicJournalService.addElectronicJournal(electronicJournalToSave);
        ElectronicJournal electronicJournalFound = electronicJournalService.findElectronicJournalById(electronicJournalSaved.getId());
        //then
        assertNotNull(electronicJournalFound);
    }

    @Test
    void updateElectronicJournal() {
        //given
        ElectronicJournal electronicJournalSaved = electronicJournalRepository.save(new ElectronicJournal(ElectronicJournal.EventType.DONE));
        ElectronicJournal electronicJournalFound = electronicJournalRepository.findElectronicJournalByEventType(electronicJournalSaved.getEventType());
        //when
        electronicJournalFound.setEventType(ElectronicJournal.EventType.MANAGER);
        electronicJournalService.updateElectronicJournal(electronicJournalFound.getId(), electronicJournalFound);
        ElectronicJournal electronicJournalChanged = electronicJournalService.findElectronicJournalById(electronicJournalFound.getId());
        //then
        assertEquals(electronicJournalChanged.getEventType(), ElectronicJournal.EventType.MANAGER);
    }
}