package com.pekao.projektpekao.service;

import com.pekao.projektpekao.BookTestUtility;
import com.pekao.projektpekao.ElectronicJournalTestUtility;
import com.pekao.projektpekao.domain.book.Book;
import com.pekao.projektpekao.domain.ElectronicJournal;
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
//@TestPropertySource("/application-test.properties") //alternative in case of test issues
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
        final List<Book> booksSaved = bookRepository.saveAll(
                List.of(BookTestUtility.createBookWithPublisher(Book.Publisher.ZNAK), BookTestUtility.createBookWithPublisher(Book.Publisher.PWN)));
        //when
        List<ElectronicJournal> electronicJournalsFound = electronicJournalService.findAllElectronicJournals();
        //then
        assertThat(electronicJournalsFound, hasSize(2));
    }

    @Test
    void findElectronicJournalById() {
        //given
        Book bookSavedWithPublisher = bookRepository.save(BookTestUtility.createBookWithPublisher(Book.Publisher.PWN));
        //when
        ElectronicJournal electronicJournalFound = electronicJournalService.findElectronicJournalById(bookSavedWithPublisher.getElectronicJournal().getId());
        //then
        assertEquals(bookSavedWithPublisher.getElectronicJournal().getId(), electronicJournalFound.getId());

    }

    @Test
    void removeElectronicJournalById() {
        //given
        ElectronicJournal electronicJournalSaved = electronicJournalRepository.save(ElectronicJournalTestUtility.createElectronicJournalEntryWithEventType(ElectronicJournal.EventType.TO_DO));
        //when
        electronicJournalService.removeElectronicJournalById(electronicJournalSaved.getId());
        //then
        assertThrows(IllegalStateException.class, () -> electronicJournalService.findElectronicJournalById(electronicJournalSaved.getId()));

    }

    @Test
    void addElectronicJournal() {
        //given
        ElectronicJournal electronicJournalToSave = ElectronicJournalTestUtility.createElectronicJournalEntryWithEventType(ElectronicJournal.EventType.MANAGER);
        //when
        ElectronicJournal electronicJournalSaved = electronicJournalService.addElectronicJournal(electronicJournalToSave);
        ElectronicJournal electronicJournalFound = electronicJournalService.findElectronicJournalById(electronicJournalSaved.getId());
        //then
        assertNotNull(electronicJournalFound);
    }

    @Test
    void updateElectronicJournal() {
        //given
        ElectronicJournal electronicJournalSaved = electronicJournalRepository.save(ElectronicJournalTestUtility.createElectronicJournalEntryWithEventType(ElectronicJournal.EventType.TO_DO));
        ElectronicJournal eJToChange = ElectronicJournal.builder()
                .from(electronicJournalSaved)
                .eventType(ElectronicJournal.EventType.DONE)
                .buildFrom();
        //when
        ElectronicJournal electronicJournalChanged = electronicJournalService.updateElectronicJournal(eJToChange);
        //then
        assertEquals(electronicJournalChanged.getEventType(), eJToChange.getEventType());
    }
}
