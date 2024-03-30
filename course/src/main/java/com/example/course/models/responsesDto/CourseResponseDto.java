package com.example.course.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseDto {
    private Long id;
    private String title;
    private String description;
    private List<Long> authorsId;
    private Integer categoryId;
}
