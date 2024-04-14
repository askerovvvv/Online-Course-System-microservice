package com.example.auth.models.responsesDto;

import com.example.auth.models.entity.Faculty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupResponseDto {

    private Long id;
    private String groupName;
    private Integer facultyId;
    private Integer numberOfStudents;
}
