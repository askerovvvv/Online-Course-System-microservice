package com.example.auth.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    @NotEmpty(message = "The name cannot be empty")
    @Size(max = 30, message = "Name too long")
    String firstname;

    @NotEmpty(message = "The lastname cannot be empty")
    @Size(max = 30, message = "Lastname too long")
    String lastname;

    @Email(message = "Please enter the correct email format.")
    String email;

    @NotEmpty(message = "Password can not be empty.")
    String password;

    @NotEmpty(message = "The password confirmation field cannot be empty.")
    String passwordConfirm;

}
