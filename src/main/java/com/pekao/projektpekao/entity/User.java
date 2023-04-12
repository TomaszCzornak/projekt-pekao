package com.pekao.projektpekao.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String createdAt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
            .format(new Date());

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private List<Comment> commentList;

    public User() {

    }



    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String createdAt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
                .format(new Date());
        private List <Comment> commentList;

        public Builder( Long id, String firstName, String lastName, String email) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;

        }

        public Builder createdAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder commentList(List<Comment> commentList) {
            commentList.forEach(comment -> comment.setUser(build()));
            this.commentList = commentList;
            return this;
        }
        public User build() {
            return new User(this);
        }

    }

    private User(Builder builder) {
        id = builder.id;
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
        commentList = builder.commentList;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }
}