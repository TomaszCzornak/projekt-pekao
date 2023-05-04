package com.pekao.projektpekao.repository;

import com.pekao.projektpekao.domain.ElectronicJournal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectronicJournalRepository extends JpaRepository<com.pekao.projektpekao.domain.ElectronicJournal, Long> {

    ElectronicJournal findElectronicJournalByEventType(ElectronicJournal.EventType eventType);
}
