package com.pekao.projektpekao.entity;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;

    public Comment() {
    }

    public Comment(String content, User user, Book book) {
        this.content = content;
        this.user = user;
        this.book = book;
    }

    public Comment(String content, Book book) {
        this.content = content;
        this.book = book;
    }

    public Comment(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
