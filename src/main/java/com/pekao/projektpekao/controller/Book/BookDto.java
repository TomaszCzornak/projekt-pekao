package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.controller.Author.AuthorDto;
import com.pekao.projektpekao.controller.Comments.CommentDto;
import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.entity.ElectronicJournal;

import java.util.List;
public class BookDto {

    private Long id;
    private String title;
    private AuthorDto authorDto;
    private List<CommentDto> commentDtoList;

    private ElectronicJournal electronicJournal;
    private Book.Publisher publisher;



    public BookDto() {
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public List<CommentDto> getcommentDtoList() {
        return commentDtoList;
    }

    public ElectronicJournal getElectronicJournal() {
        return electronicJournal;
    }

    public Book.Publisher getPublisher() {
        return publisher;
    }
    public static BookDto.Builder builder() {
        return new BookDto.Builder();
    }
    public static final class Builder {
        private Long id;
        private String title;
        private AuthorDto authorDto;
        private List<CommentDto> commentDtoList;
        private ElectronicJournal electronicJournal;
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

        public Builder commentDtoList(List<CommentDto> commentDtoList) {
            this.commentDtoList = commentDtoList;
            return this;
        }

        public Builder electronicJournal(ElectronicJournal electronicJournal) {
            this.electronicJournal = electronicJournal;
            return this;
        }

        public Builder publisher(Book.Publisher publisher) {
            this.publisher = publisher;
            return this;
        }

        public BookDto build() {
            BookDto bookDto = new BookDto();
            bookDto.id = this.id;
            bookDto.commentDtoList = this.commentDtoList;
            bookDto.publisher = this.publisher;
            bookDto.title = this.title;
            bookDto.authorDto = this.authorDto;
            bookDto.electronicJournal = this.electronicJournal;
            return bookDto;
        }
    }
}

