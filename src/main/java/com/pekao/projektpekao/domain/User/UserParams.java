package com.pekao.projektpekao.domain.User;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserParams {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate createdAt;
}
