package com.pekao.projektpekao.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ElectronicJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date date;
    private String name;
    @ManyToOne
    private User user;

    public enum EventType {
        TO_DO,
        DONE,
        MANAGER,
        WIP;
    }
    public ElectronicJournal(EventType eventType) {
   }

    public ElectronicJournal() {

    }

    public ElectronicJournal(Date date, String name, User user, EventType eventType) {
        this.date = new Date();
        this.name = name;
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
