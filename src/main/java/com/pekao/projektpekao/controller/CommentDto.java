package com.pekao.projektpekao.controller;


import lombok.Getter;

@Getter
public class CommentDto {

    private Long id;
    private String content;
    private UserDto userDto;
    private BookDto bookDto;

    protected CommentDto() {
    }


    public static final class Builder {
        private Long id;
        private String content;
        private UserDto userDto;
        private BookDto bookDto;

        private Builder() {
        }


        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder userDto(UserDto userDto) {
            this.userDto = userDto;
            return this;
        }

        public Builder bookDto(BookDto bookDto) {
            this.bookDto = bookDto;
            return this;
        }

        public CommentDto build() {
            CommentDto commentDto = new CommentDto();
            commentDto.userDto = this.userDto;
            commentDto.bookDto = this.bookDto;
            commentDto.content = this.content;
            commentDto.id = this.id;
            return commentDto;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
