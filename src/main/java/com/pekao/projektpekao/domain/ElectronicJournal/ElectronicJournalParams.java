package com.pekao.projektpekao.domain.ElectronicJournal;

import com.pekao.projektpekao.domain.Book.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class ElectronicJournalParams {
    private final Long id;
    private final String name;
    private final ElectronicJournal.EventType eventType;
    private final Book.Publisher publisher;

    public ElectronicJournalParams(Long id, String name, ElectronicJournal.EventType eventType, Book.Publisher publisher) {
        if (name == null || eventType == null) {
            throw new IllegalStateException("On of required values is null: [%s]".formatted(List.of(id, name, eventType)));
        }

        this.id = id;
        this.name = name;
        this.eventType = eventType;
        this.publisher = publisher;

    }

}
