package com.pekao.projektpekao.controller.Users;


import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserDto {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate createdAt;


    protected UserDto() {
    }


    public static final class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private LocalDate createdAt;

        private Builder() {
        }



        public Builder id(Long id) {
            this.id = id;
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
        public UserDto build() {
            UserDto userDto = new UserDto();
            userDto.id = this.id;
            userDto.email = this.email;
            userDto.lastName = this.lastName;
            userDto.firstName = this.firstName;
            userDto.createdAt = this.createdAt;
            return userDto;
        }
    }


    public static Builder builder() {
        return new Builder();
    }
}
