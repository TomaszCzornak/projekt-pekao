package com.pekao.projektpekao.entity;

import javax.persistence.*;
import java.util.List;

import static com.pekao.projektpekao.entity.ElectronicJournal.EventType.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> commentList;
    @OneToOne(cascade = CascadeType.ALL)
    private ElectronicJournal electronicJournal;
    private Publisher publisher;

    public enum Publisher {
        WYDAWNICTWO_LITERACKIE,
        PWN,
        ZNAK,
        AGORA,
    }

    public Book() {
    }



    public Book(String title, Author author, List<Comment> commentList,
                ElectronicJournal electronicJournal, Publisher publisher) {
        this.title = title;
        this.author = author;
        commentList.forEach(comment -> comment.setBook(this));
        this.commentList = commentList;
        this.electronicJournal = createElectronicJournalEventType(publisher);
        this.publisher = publisher;
    }

    public Book(String title, Author author, List<Comment> commentList, Publisher publisher) {
        this.title = title;
        this.author = author;
        commentList.forEach(comment -> comment.setBook(this));
        this.commentList = commentList;
        this.publisher = publisher;
        this.electronicJournal = createElectronicJournalEventType(publisher);

    }

    private ElectronicJournal createElectronicJournalEventType(Publisher publisher) {
        return switch (publisher) {
                case WYDAWNICTWO_LITERACKIE -> new ElectronicJournal(ElectronicJournal.EventType.MANAGER);
                case PWN -> new ElectronicJournal(ElectronicJournal.EventType.DONE);
                case ZNAK -> new ElectronicJournal(ElectronicJournal.EventType.TO_DO);
                case AGORA -> new ElectronicJournal(WIP);
                default -> throw new IllegalArgumentException("Invalid example: " + publisher);
            };
        }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setElectronicJournal(ElectronicJournal electronicJournal) {
        this.electronicJournal = electronicJournal;
    }
    public ElectronicJournal getElectronicJournal() {
        return electronicJournal;
    }
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
