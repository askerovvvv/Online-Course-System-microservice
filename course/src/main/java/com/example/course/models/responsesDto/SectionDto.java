package com.example.course.models.responsesDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionDto {

    private Long courseId;
    private String title;
    private int sectionOrder;

}
