package com.pekao.projektpekao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    private Author author;
    // TODO delete after DTO
    @OneToMany(mappedBy = "book", cascade = CascadeType.MERGE)
    private List<Comment> commentList;

    @OneToOne(cascade = CascadeType.ALL)
    private ElectronicJournal electronicJournal;
    @Enumerated(EnumType.STRING)
    private Publisher publisher;

    public enum Publisher {
        WYDAWNICTWO_LITERACKIE,
        PWN,
        ZNAK,
        AGORA,
    }

    public Book() {
    }


    public static ElectronicJournal createElectronicJournalEventType(Publisher publisher) {
        return switch (publisher) {
            case WYDAWNICTWO_LITERACKIE -> ElectronicJournal.builder()
                    .eventType(ElectronicJournal.EventType.MANAGER)
                    .name("Tu wydawcą jest Wydawnictwo Literackie")
                    .buildNew();
            case PWN -> ElectronicJournal.builder()
                    .eventType(ElectronicJournal.EventType.DONE)
                    .name("Tu wydawcą jest PWN")
                    .buildNew();
            case ZNAK -> ElectronicJournal.builder()
                    .eventType(ElectronicJournal.EventType.TO_DO)
                    .name("Tu wydawcą jest ZNAK")
                    .buildNew();
            case AGORA -> ElectronicJournal.builder()
                    .eventType(ElectronicJournal.EventType.WIP)
                    .name("Tu wydawcą jest AGORA")
                    .buildNew();
            default -> throw new IllegalArgumentException("Invalid example: " + publisher);
        };
    }

    public Book(Long id, String title, Author author, List<Comment> commentList,
                ElectronicJournal electronicJournal, Publisher publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.commentList = commentList;
        this.electronicJournal = createElectronicJournalEventType(publisher);
        this.publisher = publisher;
    }

    public static class Builder {
        private Long id;
        private String title;
        private Author author;
        private List<Comment> commentList;
        private ElectronicJournal electronicJournal;
        private Publisher publisher;

        private Builder() {
        }

        public Builder from(Book book) {
            this.id = book.id;
            this.title = book.title;
            this.author = book.author;
            this.commentList = book.commentList;
            this.electronicJournal = createElectronicJournalEventType(publisher);
            this.publisher = book.publisher;
            return this;
        }

        public Builder fromExisting(Book book) {
            this.id = book.id;
            this.title = book.title;
            this.author = book.author;
            this.commentList = book.commentList;
            this.publisher = book.publisher;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder author(Author author) {
            this.author = author;
            return this;
        }

        public Builder commentList(List<Comment> commentList) {
            this.commentList = commentList;
            return this;
        }

        public Builder electronicJournal(ElectronicJournal electronicJournal) {
            this.electronicJournal = electronicJournal;
            return this;
        }

        public Builder publisher(Publisher publisher) {
            this.publisher = publisher;
            return this;
        }

        public Book buildNewEntity() {
            if (id != null) {
                throw new IllegalStateException("Id must be null if you want create new Entity");
            }

            return new Book(null, title, author, commentList, electronicJournal, publisher);
        }

        public Book build() {
            return new Book(id, title, author, commentList, electronicJournal, publisher);
        }
    }

    public static Builder builder() {
        return new Book.Builder();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public ElectronicJournal getElectronicJournal() {
        return electronicJournal;
    }

    public Publisher getPublisher() {
        return publisher;
    }
}
