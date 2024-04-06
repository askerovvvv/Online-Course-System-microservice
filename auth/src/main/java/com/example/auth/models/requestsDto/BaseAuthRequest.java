package com.example.auth.models.requestsDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseAuthRequest {
    @Email(message = "Please enter the correct email format.")
    private String email;

    @NotEmpty(message = "Password can not be empty.")
    String password;

    @NotEmpty(message = "The password confirmation field cannot be empty.")
    String passwordConfirm;
}
