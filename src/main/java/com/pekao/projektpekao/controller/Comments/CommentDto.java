package com.pekao.projektpekao.controller.Comments;


import com.pekao.projektpekao.controller.Book.BookForCommentDto;
import com.pekao.projektpekao.controller.Users.UserDto;
import lombok.Getter;

@Getter
public class CommentDto {

    private Long id;
    private String content;
    private UserDto userDto;
    private BookForCommentDto bookForCommentDto;




    public static final class Builder {
        private Long id;
        private String content;
        private UserDto userDto;
        private BookForCommentDto bookForCommentDto;

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

        public Builder bookForCommentDto(BookForCommentDto bookForCommentDto) {
            this.bookForCommentDto = bookForCommentDto;
            return this;
        }

        public CommentDto build() {
            CommentDto commentDto = new CommentDto();
            commentDto.userDto = this.userDto;
            commentDto.bookForCommentDto = this.bookForCommentDto;
            commentDto.content = this.content;
            commentDto.id = this.id;
            return commentDto;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
