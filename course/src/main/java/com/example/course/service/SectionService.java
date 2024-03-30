package com.example.course.service;

import com.example.course.models.responsesDto.SectionDto;

import java.util.List;

public interface SectionService {
    void addSection(SectionDto sectionData);
    List<SectionDto> findAllSections();
}
