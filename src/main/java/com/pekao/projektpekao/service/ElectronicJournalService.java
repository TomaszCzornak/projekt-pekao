package com.pekao.projektpekao.service;

import com.pekao.projektpekao.entity.ElectronicJournal;
import com.pekao.projektpekao.infrastructure.ElectronicJournalDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ElectronicJournalService {

    @Resource(name = "ElectronicJournalJpaImpl")
    private final ElectronicJournalDao electronicJournalDao;

    public ElectronicJournalService(ElectronicJournalDao electronicJournalDao) {
        this.electronicJournalDao = electronicJournalDao;
    }

    public List<ElectronicJournal> findAllElectronicJournals() {
        return electronicJournalDao.findAll();
    }

    public Optional<ElectronicJournal> findElectronicJournalById(Long id) {
        return electronicJournalDao.findById(id);
    }

    public void removeElectronicJournalById(Long id) {
        electronicJournalDao.deleteElectronicJournalById(id);
    }

    public ElectronicJournal addElectronicJournal(ElectronicJournal electronicJournal) {
        return electronicJournalDao.addElectronicJournal(electronicJournal);
    }

}
