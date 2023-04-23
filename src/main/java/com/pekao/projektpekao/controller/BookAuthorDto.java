package com.pekao.projektpekao.controller;


import lombok.Getter;

@Getter
public class BookAuthorDto {

    private Long id;
    private String title;

    protected BookAuthorDto(){
    }
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String title;

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

        public BookAuthorDto build() {
            BookAuthorDto bookAuthorDto = new BookAuthorDto();
            bookAuthorDto.id = this.id;
            bookAuthorDto.title = this.title;
            return bookAuthorDto;
        }
    }
}
