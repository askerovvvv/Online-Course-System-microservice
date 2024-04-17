package com.example.course.service.impl;

import com.example.course.mapper.SectionMapper;
import com.example.course.models.Course;
import com.example.course.models.Section;
import com.example.course.models.requestsDto.SectionRequestDto;
import com.example.course.repository.SectionRepository;
import com.example.course.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SectionServiceImplTest {

    @Mock
    private SectionRepository sectionRepository;
    @Mock
    private CourseService courseService;

    private SectionServiceImpl sectionService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sectionService = new SectionServiceImpl(
                sectionRepository,
                courseService
        );
    }

    @Test
    void TestAddSection() {
        // Given
        Course mockCourse = new Course();
        mockCourse.setTitle("test course");

        SectionRequestDto mockSectionRequestDto = new SectionRequestDto();
        mockSectionRequestDto.setTitle("test section");
        mockSectionRequestDto.setCourseId(1L);

        Section mockSection = SectionMapper.INSTANCE.toSection(mockSectionRequestDto);
        mockSection.setCourse(mockCourse);

        // Mock actions
        when(courseService.findCourseById(1L)).thenReturn(mockCourse);
        when(sectionRepository.save(any(Section.class))).thenReturn(mockSection);

        // When
        sectionService.addSection(mockSectionRequestDto);

        // Then
        assertEquals(mockSectionRequestDto.getTitle(), mockSection.getTitle());
        verify(sectionRepository, times(1)).save(mockSection);
    }

    @Test
    void findAllSections() {
    }

    @Test
    void findSectionById() {
    }

    @Test
    void deleteSectionById() {
    }
}