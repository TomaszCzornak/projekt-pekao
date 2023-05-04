package com.pekao.projektpekao.domain;

import com.pekao.projektpekao.domain.book.Book;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Book book;

    protected Comment() {
    }

    private Comment(Long id, String content, User user, Book book) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.book = book;
    }

    public static class Builder {
        private Long id;
        private String content;
        private User user;
        private Book book;

        public Builder() {
        }

        public Builder from(Comment comment) {
            this.id = comment.id;
            this.content = comment.content;
            this.user = comment.user;
            this.book = comment.book;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder book(Book book) {
            this.book = book;
            return this;
        }

        public Comment build() {

            return new Comment(id, content, user, book);
        }

        public Comment buildNewEntity() {
            if (id != null) {
                throw new IllegalStateException("To  new object, id needs to be null");
            }
            return new Comment(null, content, user, book);

        }

    }

    public static Builder builder() {
        return new Comment.Builder();
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public void setBook(final Book book) {
        this.book = book;
    }
}
