package com.pekao.projektpekao.service;

import com.pekao.projektpekao.ElectronicJournalTestUtility;
import com.pekao.projektpekao.controller.ElectronicJournal.ElectronicJournalDto;
import com.pekao.projektpekao.controller.ElectronicJournal.ElectronicJournalDtoMapper;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournalParams;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournalParamsMapper;
import com.pekao.projektpekao.repository.AuthorRepository;
import com.pekao.projektpekao.repository.BookRepository;
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
        final List<ElectronicJournal> electronicJournalList = ElectronicJournalTestUtility.createElectronicJournalList();
        electronicJournalRepository.saveAll(electronicJournalList);
        //when
        List<ElectronicJournal> electronicJournalsFound = electronicJournalService.findAllElectronicJournals();
        long journalsCreated = electronicJournalList.stream().count();
        //then
        assertThat(electronicJournalsFound, hasSize((int) journalsCreated));
    }

    @Test
    void findElectronicJournalById() {
        //given
        ElectronicJournal electronicJournal = electronicJournalRepository.save(ElectronicJournalTestUtility.createElectronicJournalEntryWithEventType(ElectronicJournal.EventType.TO_DO));
        //when
        ElectronicJournal electronicJournalFound = electronicJournalService.findElectronicJournalById(electronicJournal.getId());
        //then
        assertEquals(electronicJournal.getId(), electronicJournalFound.getId());

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
        ElectronicJournalParams electronicJournalToSave = ElectronicJournalTestUtility.createElectronicJournalParamsEntryWithEventType(ElectronicJournal.EventType.MANAGER);
        //when
        ElectronicJournal electronicJournalSaved = electronicJournalService.addElectronicJournal(electronicJournalToSave);
        ElectronicJournal electronicJournalFound = electronicJournalService.findElectronicJournalById(electronicJournalSaved.getId());
        //then
        assertNotNull(electronicJournalFound);
    }

    @Test
    void updateElectronicJournal() {
        //given
        ElectronicJournal electronicJournalSaved = electronicJournalRepository.save(
                ElectronicJournalTestUtility.createElectronicJournalEntryWithEventType(ElectronicJournal.EventType.TO_DO));
        ElectronicJournal eJToChange = ElectronicJournal.builder()
                .from(electronicJournalSaved)
                .eventType(ElectronicJournal.EventType.DONE)
                .buildFrom();
        ElectronicJournalDto electronicJournalDtoMapped = ElectronicJournalDtoMapper.toElectronicJournalDto(eJToChange);
        //when
        ElectronicJournal electronicJournalChanged = electronicJournalService.updateElectronicJournal(ElectronicJournalParamsMapper.toElectronicJournalParams(electronicJournalDtoMapped));
        //then
        assertEquals(electronicJournalChanged.getEventType(), eJToChange.getEventType());
    }
}
