package com.pekao.projektpekao.repository;

import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectronicJournalRepository extends JpaRepository<ElectronicJournal, Long> {

    ElectronicJournal findElectronicJournalByEventType(ElectronicJournal.EventType eventType);
    ElectronicJournal findElectronicJournalByName(String name);
}
