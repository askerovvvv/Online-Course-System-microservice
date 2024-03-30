package com.example.course.service.impl;

import com.example.course.client.CourseUserClient;
import com.example.course.exceptions.CustomBadRequestException;
import com.example.course.exceptions.NotFoundException;
import com.example.course.models.Author;
import com.example.course.models.Category;
import com.example.course.models.Course;
import com.example.course.models.dto.CourseDto;
import com.example.course.models.dto.AuthorDto;
import com.example.course.repository.CourseRepository;
import com.example.course.service.AuthorService;
import com.example.course.service.CategoryService;
import com.example.course.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseImplTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CategoryService categoryService;
    @Mock
    private CourseUserClient courseUserClient;
    @Mock
    private AuthorService authorService;

    private CourseService mockCourseService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockCourseService = new CourseImpl(
                courseRepository,
                categoryService,
                courseUserClient,
                authorService
        );
    }

    @Test
    void testGetAllCourses() {
        // Given
        Course course1 = new Course();
        Course course2 = new Course();
        List<Course> mockCourses = new ArrayList<>(List.of(course1, course2));

        // Mock actions
        when(courseRepository.findAll()).thenReturn(mockCourses);

        // When
        List<CourseDto> actualResult = mockCourseService.findAllCourses();

        // Then
        assertEquals(2, actualResult.size());
    }


    @Test
    void testGetCourseById_Success() {
        // Given
        Course mockCourse = new Course();

        // Mock action
        when(courseRepository.findById(any())).thenReturn(Optional.of(mockCourse));

        // When
        CourseDto actualResult = mockCourseService.findCourseDtoById(1L);

        // Then
        assertNotNull(actualResult);
        assertInstanceOf(CourseDto.class, actualResult);
    }

    @Test
    void testGetCourseById_Failed() {
        // Mock action
        when(courseRepository.findById(any())).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class, () -> mockCourseService.findCourseDtoById(1L));
    }

    @Test
    void testAddCourse_Success() {
        // Given
        CourseDto mockCourseDto = new CourseDto();
        mockCourseDto.setCategoryId(2);
        mockCourseDto.setTeacherId(1L);

        AuthorDto mockAuthorDto = new AuthorDto();
        mockAuthorDto.setEmailVerified(true);

        Category mockCategory = new Category();

        // Mock actions
        when(courseUserClient.getUserById(1L)).thenReturn(mockAuthorDto);
        when(categoryService.getCategoryById(2)).thenReturn(mockCategory);
        when(authorService.findAuthorByEmailOrCreate(mockAuthorDto)).thenReturn(new Author());
        when(courseRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        CourseDto actualResult = mockCourseService.addCourse(mockCourseDto);

        // Then
        assertNotNull(actualResult);
        assertEquals(mockCourseDto.getTeacherId(), actualResult.getTeacherId());
        assertEquals(mockCourseDto.getCategoryId(), actualResult.getCategoryId());
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void testAddCourse_With_InactiveUser() {
        // Given
        CourseDto mockCourseDto = new CourseDto();
        AuthorDto mockAuthorDto = new AuthorDto();
        mockAuthorDto.setEmailVerified(false);

        // Mock actions
        when(courseUserClient.getUserById(any())).thenReturn(mockAuthorDto);

        // Then
        assertThrows(CustomBadRequestException.class, () -> mockCourseService.addCourse(mockCourseDto));
        verify(courseRepository, never()).save(any());
    }

    @Test
    void testAddCourse_With_CategoryNotExists() {
        // Given
        CourseDto mockCourseDto = new CourseDto();
        mockCourseDto.setCategoryId(2);
        AuthorDto mockAuthorDto = new AuthorDto();
        mockAuthorDto.setEmailVerified(true);

        // Mock actions
        when(courseUserClient.getUserById(any())).thenReturn(mockAuthorDto);
        when(categoryService.getCategoryById(any(Integer.class))).thenThrow(NotFoundException.class);

        // Then
        assertThrows(NotFoundException.class, () -> mockCourseService.addCourse(mockCourseDto));
        verify(courseRepository, never()).save(any());
    }

    @Test
    void testDeleteCourse_Success() {
        // Given
        Course mockCourse = new Course();

        // Mock actions
        when(courseRepository.findById(any())).thenReturn(Optional.of(mockCourse));

        // When
        mockCourseService.deleteCourseById(1L);

        // Then
        verify(courseRepository, times(1)).delete(mockCourse);
    }

    @Test
    void testDeleteCourse_NotFound_Failed() {
        // Mock actions
        when(courseRepository.findById(any())).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class, () -> mockCourseService.deleteCourseById(1L));
        verify(courseRepository, never()).delete(any());
    }


}