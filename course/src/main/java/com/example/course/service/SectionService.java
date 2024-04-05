package com.example.course.service;

import com.example.course.models.requestsDto.SectionRequestDto;
import com.example.course.models.responsesDto.SectionResponseDto;

import java.util.List;

public interface SectionService {
    void addSection(SectionRequestDto sectionData);
    List<SectionResponseDto> findAllSections();
    SectionResponseDto findSectionById(Long sectionId);
    void deleteSectionById(Long sectionId);
    // TODO update section
}
