package com.pekao.projektpekao.infrastructure;

import com.pekao.projektpekao.entity.ElectronicJournal;
import com.pekao.projektpekao.repository.ElectronicJournalRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository("ElectronicJournalJpaImpl")
public class ElectronicJournalJpaImpl implements ElectronicJournalDao {

    private final ElectronicJournalRepository electronicJournalRepository;

    public ElectronicJournalJpaImpl(ElectronicJournalRepository electronicJournalRepository) {
        this.electronicJournalRepository = electronicJournalRepository;
    }

    @Override
    public List<ElectronicJournal> findAll() {
        return electronicJournalRepository.findAll();
    }

    @Override
    public Optional<ElectronicJournal> findById(Long id) {
        return electronicJournalRepository.findById(id);
    }

    @Override
    public ElectronicJournal addElectronicJournal(ElectronicJournal electronicJournal) {
        return electronicJournalRepository.save(electronicJournal);
    }

    @Override
    public void deleteElectronicJournalById(Long id) {
        electronicJournalRepository.deleteById(id);

    }
}
