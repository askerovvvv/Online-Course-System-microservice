package com.example.auth.models.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest extends BaseAuthRequest {

    @NotEmpty(message = "Password can not be empty.")
    private String password;

}

