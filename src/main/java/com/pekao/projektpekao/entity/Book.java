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
    @ManyToOne
    private Author author;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Comment> commentList;
    @OneToOne(cascade = CascadeType.ALL)
    private ElectronicJournal electronicJournal;


    public enum Publisher {
        WYDAWNICTWO_LITERACKIE,
        PWN,
        ZNAK,
        CZARNE,
        AGORA,
        MUZA
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
    }

    public Book(String title, Author author, List<Comment> commentList) {
        this.title = title;
        this.author = author;
        commentList.forEach(comment -> comment.setBook(this));
        this.commentList = commentList;
    }

    public static ElectronicJournal createElectronicJournalEventType(Publisher publisher) {
            return switch (publisher) {
                case WYDAWNICTWO_LITERACKIE -> new ElectronicJournal(MANAGER);
                case PWN -> new ElectronicJournal(DONE);
                case ZNAK -> new ElectronicJournal(TO_DO);
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
}
