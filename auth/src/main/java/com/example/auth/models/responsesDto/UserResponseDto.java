package com.example.auth.models.responsesDto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserResponseDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Boolean emailVerified;
}
