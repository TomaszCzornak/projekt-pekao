package com.pekao.projektpekao.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class ElectronicJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String created = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
            .format(new Date());
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    public enum EventType {
        TO_DO,
        DONE,
        MANAGER,
        WIP;
    }
    public ElectronicJournal(EventType eventType) {
        this.eventType = eventType;
   }

    public ElectronicJournal() {

    }

    public ElectronicJournal(String created, String name, User user, EventType eventType) {
        this.created = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
                .format(new Date());
        this.name = name;
        this.user = user;
        this.eventType = eventType;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    private void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

}
