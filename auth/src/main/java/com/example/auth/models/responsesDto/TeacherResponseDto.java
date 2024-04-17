package com.example.auth.models.responsesDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TeacherResponseDto extends UserResponseDto {
    private int experience;
    private double salary;
    private String officeNumber;
    private String phoneNumber;
    private LocalDateTime hireDate;
}
