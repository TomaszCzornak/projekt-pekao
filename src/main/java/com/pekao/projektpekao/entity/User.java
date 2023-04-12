package com.pekao.projektpekao.entity;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private List<Comment> commentList;

    protected User() {}

    private User(
            final Long id, final String firstName, final String lastName, final String email, final LocalDate createdAt,
            final List<Comment> commentList
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createdAt = createdAt;
        this.commentList = commentList;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private LocalDate createdAt;
        private List<Comment> commentList;

        public Builder(Long id, String firstName, String lastName, String email) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public Builder() {}

        public Builder from(User user) {
            this.id = user.id;
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.email = user.email;
            this.createdAt = user.createdAt;
            this.commentList = user.commentList;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder createdAt(LocalDate createdAt) {
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
            if (Stream.of(id, firstName, lastName, email, createdAt, commentList).anyMatch(Objects::isNull)) {
                throw new IllegalStateException("On of required values is null: [%s]".formatted(List.of(id, firstName, lastName, email, createdAt, commentList)));
            }

            return new User(id, firstName, lastName, email, createdAt, commentList);
        }

        public User buildNewEntity() {
            if (id != null) {
                throw new IllegalStateException("Id must be null if you want create new Entity");
            }

            return new User(null, firstName, lastName, email, createdAt, commentList);
        }
    }

    public static Builder builder() {
        return new User.Builder();
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }
}
