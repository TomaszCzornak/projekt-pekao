package com.pekao.projektpekao.service;

import com.pekao.projektpekao.controller.ElectronicJournal.ElectronicJournalEntityMapper;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournalParams;
import com.pekao.projektpekao.infrastructure.ElectronicJournalDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public ElectronicJournal findElectronicJournalById(Long id) {
        return electronicJournalDao.findById(id).orElseThrow(()->new IllegalStateException("Cannot find electronic Journal with id " + id));
    }

    public void removeElectronicJournalById(Long id) {
        electronicJournalDao.deleteElectronicJournalById(id);
    }

    public ElectronicJournal addElectronicJournal(ElectronicJournalParams electronicJournalParams) {
        ElectronicJournal electronicJournalEntity = ElectronicJournalEntityMapper.toElectronicJournalEntity(electronicJournalParams);
        return electronicJournalDao.addElectronicJournal(electronicJournalEntity);
    }

    public ElectronicJournal updateElectronicJournal(ElectronicJournalParams electronicJournalParams) {
        ElectronicJournal electronicJournal = ElectronicJournalEntityMapper.toElectronicJournalEntity(electronicJournalParams);
        return electronicJournalDao.addElectronicJournal(electronicJournal);
    }

}
