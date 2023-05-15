package com.pekao.projektpekao.service;

import com.pekao.projektpekao.controller.Author.AuthorEntityMapper;
import com.pekao.projektpekao.controller.ElectronicJournal.ElectronicJournalDto;
import com.pekao.projektpekao.controller.ElectronicJournal.ElectronicJournalDtoMapper;
import com.pekao.projektpekao.domain.Author.AuthorFactory;
import com.pekao.projektpekao.domain.Author.AuthorParams;
import com.pekao.projektpekao.domain.Author.Author;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournalParams;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournalParamsMapper;
import com.pekao.projektpekao.infrastructure.AuthorDao;
import com.pekao.projektpekao.infrastructure.ElectronicJournalDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class AuthorService {

    @Resource(name = "AuthorDaoJpaImpl")
    private final AuthorDao authorDaoJpa;

    @Resource(name = "ElectronicJournalJpaImpl")
    private final ElectronicJournalDao electronicJournalJpa;

    public AuthorService(@Qualifier("AuthorDaoJpaImpl") AuthorDao authorDaoJpa, ElectronicJournalDao electronicJournalDao) {
        this.authorDaoJpa = authorDaoJpa;
        this.electronicJournalJpa = electronicJournalDao;
    }

    public List findAllAuthors() {
        return authorDaoJpa.findAll();
    }

    public Author findAuthorById(Long id) {
        return authorDaoJpa.findById(id).orElseThrow(()->new IllegalStateException("Cannot find an author with ID: " + id));
    }
    public Author findAuthorByLastName(String lastName) {
        return authorDaoJpa.findByLastName(lastName);
    }

    public void removeAuthorById(Long id) {
        authorDaoJpa.deleteAuthorById(id);
    }

    public Author addAuthor(AuthorParams authorParams) {
        ElectronicJournal electronicJournalToSave = AuthorFactory.createElectronicJournalEventTypeForAuthor(authorParams.getFirstName(), authorParams.getLastName());
        electronicJournalJpa.addElectronicJournal(electronicJournalToSave);
        Author authorEntity = AuthorEntityMapper.toAuthorEntity(authorParams);
        return authorDaoJpa.addAuthor(authorEntity);
    }

    public Author updateAuthor(AuthorParams authorParams) {
        Author authorMapped = AuthorEntityMapper.toAuthorEntity(authorParams);
        return authorDaoJpa.addAuthor(authorMapped);
    }
}
