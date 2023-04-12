package com.pekao.projektpekao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(name = "bookId")
    @JsonIgnore
    private Book book;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comment(String content, User user, Book book) {
        this.content = content;
        this.user = user;
        this.book = book;
    }
    public Comment(User user, Book book) {
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
