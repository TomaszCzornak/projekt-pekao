package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.controller.Author.AuthorDto;
import com.pekao.projektpekao.controller.Comments.CommentDto;
import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.entity.ElectronicJournal;

import java.util.List;

public class BookForCommentDto {

    private Long id;
    private String title;
    private AuthorDto authorDto;
    private Book.Publisher publisher;

    public BookForCommentDto() {
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public Book.Publisher getPublisher() {
        return publisher;
    }

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public static BookForCommentDto.Builder builder() {
        return new BookForCommentDto.Builder();
    }
    public static final class Builder {
        private Long id;
        private String title;
        private AuthorDto authorDto;
        private Book.Publisher publisher;
        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder author(AuthorDto authorDto) {
            this.authorDto = authorDto;
            return this;
        }
        public Builder publisher(Book.Publisher publisher) {
            this.publisher = publisher;
            return this;
        }
        public BookForCommentDto build() {
            BookForCommentDto bookDto = new BookForCommentDto();
            bookDto.id = this.id;
            bookDto.title = this.title;
            bookDto.publisher = this.publisher;
            bookDto.authorDto = this.authorDto;
            return bookDto;
        }
    }
}

