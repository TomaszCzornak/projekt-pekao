   package com.pekao.projektpekao.entity;

   import java.util.List;
public class BookDto {

    private Long id;
    private String title;
    private Author author;
    // TODO delete after DTO
    private List<Comment> commentList;

    private ElectronicJournal electronicJournal;
    private Publisher publisher;

    public enum Publisher {
        WYDAWNICTWO_LITERACKIE,
        PWN,
        ZNAK,
        AGORA,
    }

    public BookDto() {
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
    public static BookDto.Builder builder() {
        return new BookDto.Builder();
    }
    public static final class Builder {
        private Long id;
        private String title;

        private Author author;
        private List<Comment> commentList;
        private ElectronicJournal electronicJournal;
        private Publisher publisher;

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

        public Builder publisher(BookDto.Publisher publisher) {
            this.publisher = publisher;
            return this;
        }

        public BookDto build() {
            BookDto bookDto = new BookDto();
            bookDto.commentList = this.commentList;
            bookDto.id = this.id;
            bookDto.publisher = this.publisher;
            bookDto.title = this.title;
            bookDto.author = this.author;
            bookDto.electronicJournal = this.electronicJournal;
            return bookDto;
        }
    }
}
