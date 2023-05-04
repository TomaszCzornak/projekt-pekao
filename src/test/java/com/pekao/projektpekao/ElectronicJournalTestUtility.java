package com.pekao.projektpekao;

import com.pekao.projektpekao.domain.ElectronicJournal;

public class ElectronicJournalTestUtility {

    public static ElectronicJournal createElectronicJournalEntryWithEventType(ElectronicJournal.EventType eventType) {
        return ElectronicJournal.builder()
                .eventType(eventType)
                .name("Testowa nazwa")
                .buildNew();
    }


}
