package com.example.course.models.requestsDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionRequestDto {
    private Long courseId;
    private String title;
    private int sectionOrder;
}
