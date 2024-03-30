package com.example.course.models.requestsDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequestDto {
    private String title;
    private String description;
    private Long authorId;
    private Integer categoryId;
}
