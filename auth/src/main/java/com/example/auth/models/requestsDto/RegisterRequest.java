package com.example.auth.models.requestsDto;

import com.example.auth.models.requestsDto.BaseAuthRequest;
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


}
