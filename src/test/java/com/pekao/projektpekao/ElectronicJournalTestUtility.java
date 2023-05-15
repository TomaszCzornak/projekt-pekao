package com.pekao.projektpekao;

import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournalParams;

import java.util.List;

public class ElectronicJournalTestUtility {

    public static ElectronicJournal createElectronicJournalEntryWithEventType(ElectronicJournal.EventType eventType) {
        return ElectronicJournal.builder()
                .eventType(eventType)
                .name("Testowa nazwa")
                .buildNew();
    }

    public static ElectronicJournalParams createElectronicJournalParamsEntryWithEventType(ElectronicJournal.EventType eventType) {
        return ElectronicJournalParams.builder()
                .eventType(eventType)
                .name("Testowa nazwa")
                .build();
    }
    public static List<ElectronicJournal> createElectronicJournalList() {
        return List.of(
                createElectronicJournalEntryWithEventType(ElectronicJournal.EventType.WIP),
                createElectronicJournalEntryWithEventType(ElectronicJournal.EventType.DONE),
                createElectronicJournalEntryWithEventType(ElectronicJournal.EventType.MANAGER)
        );
    }
}
