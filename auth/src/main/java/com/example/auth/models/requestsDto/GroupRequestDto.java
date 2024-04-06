package com.example.auth.models.requestsDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupRequestDto {

    @NotEmpty(message = "The groupName cannot be empty")
    @Size(max = 15, message = "groupName too long")
    private String groupName;

    @NotNull
    private Integer facultyId;
}
