package com.example.course.models.requestsDto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorRequestDto {
    private String firstname;
    private String lastname;
    private String email;
    private Boolean emailVerified;
}
