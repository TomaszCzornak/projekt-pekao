package com.pekao.projektpekao.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
public class ElectronicJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String created = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
            .format(new Date());
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User user;
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    public enum EventType {
        TO_DO,
        DONE,
        MANAGER,
        WIP
    }

    public ElectronicJournal() {

    }

//    public ElectronicJournal(EventType eventType) {
//        this.eventType = eventType;
//    }

    public ElectronicJournal(Long id, String created, String name, User user, EventType eventType) {
        this.id = id;
        this.created = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
                .format(new Date());
        this.name = name;
        this.user = user;
        this.eventType = eventType;
    }

    public static class ElectronicJournalBuilder {
        private Long id;
        private String created;
        private String name;
        private User user;
        private EventType eventType;

        private ElectronicJournalBuilder() {
        }

        public ElectronicJournalBuilder from(ElectronicJournal electronicJournal) {
            this.id = electronicJournal.id;
            this.created = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
                    .format(new Date());
            this.name = electronicJournal.name;
            this.user = electronicJournal.user;
            this.eventType = electronicJournal.eventType;
            return this;
        }

        public ElectronicJournalBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ElectronicJournalBuilder created(String created) {
            this.created = created;
            return this;
        }

        public ElectronicJournalBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ElectronicJournalBuilder user(User user) {
            this.user = user;
            return this;
        }

        public ElectronicJournalBuilder eventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public ElectronicJournal buildNew() {
            return new ElectronicJournal(null, created, name, user, eventType);
        }
        public ElectronicJournal buildFrom() {
            if (Stream.of(id, created, name, eventType).anyMatch(Objects::isNull)) {
                throw new IllegalStateException("On of required values is null: [%s]".formatted(List.of(id, created, name, user, eventType)));
            }

            return new ElectronicJournal(id, created, name, user, eventType);
        }
    }
    public static ElectronicJournalBuilder builder() {
        return new ElectronicJournal.ElectronicJournalBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public EventType getEventType() {
        return eventType;
    }
}
