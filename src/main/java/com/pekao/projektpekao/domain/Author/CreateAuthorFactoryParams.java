package com.pekao.projektpekao.domain.Author;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class CreateAuthorFactoryParams {

        private final Long id;
        private final String firstName;
        private final String lastName;

        public CreateAuthorFactoryParams(Long id, String firstName, String lastName) {
            if (id == null || firstName == null || lastName == null) {
                throw new IllegalStateException("On of required values is null: [%s]".formatted(List.of(id, firstName, lastName)));
            }

            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }

}
