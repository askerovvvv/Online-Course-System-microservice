package com.example.auth.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {

    @Email(message = "Please enter the correct email format.")
    private String email;

    @NotEmpty(message = "Password can not be empty.")
    private String password;

}
