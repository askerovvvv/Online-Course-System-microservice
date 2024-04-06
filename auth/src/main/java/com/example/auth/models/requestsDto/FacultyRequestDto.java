package com.example.auth.models.requestsDto;

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
public class FacultyRequestDto {

    @NotEmpty(message = "The facultyName cannot be empty")
    @Size(max = 15, message = "facultyName too long")
    private String facultyName;
}
