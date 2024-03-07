package com.example.auth.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest extends BaseAuthRequest {

    @NotEmpty(message = "The name cannot be empty")
    @Size(max = 30, message = "Name too long")
    String firstname;

    @NotEmpty(message = "The lastname cannot be empty")
    @Size(max = 30, message = "Lastname too long")
    String lastname;

    @NotEmpty(message = "Password can not be empty.")
    String password;

    @NotEmpty(message = "The password confirmation field cannot be empty.")
    String passwordConfirm;

}
