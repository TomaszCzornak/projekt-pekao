package com.pekao.projektpekao.repository;

import com.pekao.projektpekao.entity.ElectronicJournal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectronicJournalRepository extends JpaRepository<com.pekao.projektpekao.entity.ElectronicJournal, Long> {

    ElectronicJournal findElectronicJournalByEventType(ElectronicJournal.EventType eventType);
}
