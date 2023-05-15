package com.pekao.projektpekao.domain.Author;

import com.pekao.projektpekao.domain.Book.Book;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "author", cascade = CascadeType.MERGE)
    private List<Book> bookList;

    protected Author() {
    }

    private Author(Long id, String firstName, String lastName, List<Book> bookList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookList = bookList;
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

    public List<Book> getBookList() {
        return bookList;
    }

    public static final class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private List<Book> bookList;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withBookList(List<Book> bookList) {
            this.bookList = bookList;
            return this;
        }

        public Builder from(Author author) {
            this.id = author.id;
            this.firstName = author.firstName;
            this.lastName = author.lastName;
            this.bookList = author.bookList;
            return this;
        }
        public Author build() {
            if (Stream.of(id, firstName, lastName).anyMatch(Objects::isNull)) {
                throw new IllegalStateException("On of required values is null: [%s]".formatted(List.of(id, firstName, lastName)));
            }
            return new Author(id, firstName, lastName, bookList);
        }
        public Author buildNewEntity() {
            if (id != null) {
                throw new IllegalStateException("Id must not be null to create new Entity");
            }
            return new Author(null, firstName, lastName, bookList);
        }
    }

    public static Builder builder() {
        return new Author.Builder();
    }


}
