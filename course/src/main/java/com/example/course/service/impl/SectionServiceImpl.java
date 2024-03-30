package com.example.course.service.impl;

import com.example.course.mapper.SectionMapper;
import com.example.course.models.Course;
import com.example.course.models.Section;
import com.example.course.models.responsesDto.SectionDto;
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
    public void addSection(SectionDto sectionData) {
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
    public List<SectionDto> findAllSections() {
        return SectionMapper.INSTANCE.toSectionDtos(sectionRepository.findByOrderBySectionOrderAsc());
    }
}
