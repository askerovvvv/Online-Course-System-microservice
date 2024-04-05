package com.example.course.service.impl;

import com.example.course.exceptions.NotFoundException;
import com.example.course.mapper.SectionMapper;
import com.example.course.models.Course;
import com.example.course.models.Section;
import com.example.course.models.requestsDto.SectionRequestDto;
import com.example.course.models.responsesDto.SectionResponseDto;
import com.example.course.repository.SectionRepository;
import com.example.course.service.CourseService;
import com.example.course.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final CourseService courseService;

    @Override
    public void addSection(SectionRequestDto sectionData) {
        Course course = courseService.findCourseById(sectionData.getCourseId());

        // TODO: get Principal and check if Principal is author of course

        Section section = Section.builder()
                .title(sectionData.getTitle())
                .sectionOrder(sectionData.getSectionOrder())
                .course(course)
                .build();

        sectionRepository.save(section);

    }

    @Override
    public List<SectionResponseDto> findAllSections() {
        return SectionMapper.INSTANCE.toSectionDtos(sectionRepository.findByOrderBySectionOrderAsc());
    }

    @Override
    public SectionResponseDto findSectionById(Long sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new NotFoundException("Section not found!"));
        return SectionMapper.INSTANCE.toSectionDto(section);
    }

    @Override
    public void deleteSectionById(Long sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new NotFoundException("Section not found!"));

        sectionRepository.delete(section);
    }
}
