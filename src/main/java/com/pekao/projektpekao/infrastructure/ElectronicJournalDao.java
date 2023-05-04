package com.pekao.projektpekao.infrastructure;

import com.pekao.projektpekao.domain.ElectronicJournal;

import java.util.List;
import java.util.Optional;

public interface ElectronicJournalDao {

    List<ElectronicJournal> findAll();
    Optional<ElectronicJournal> findById(Long id);

    ElectronicJournal addElectronicJournal(ElectronicJournal electronicJournal);
    void deleteElectronicJournalById(Long id);
    ElectronicJournal findByEventType(ElectronicJournal.EventType eventType);
}
