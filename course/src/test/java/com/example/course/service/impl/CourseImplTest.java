package com.example.course.service.impl;

import com.example.course.client.CourseUserClient;
import com.example.course.exceptions.CustomBadRequestException;
import com.example.course.exceptions.NotFoundException;
import com.example.course.models.Course;
import com.example.course.models.dto.CourseDto;
import com.example.course.models.dto.UserDto;
import com.example.course.repository.CourseRepository;
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

    private CourseService mockCourseService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockCourseService = new CourseImpl(
                courseRepository,
                categoryService,
                courseUserClient
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
        List<CourseDto> actualResult = mockCourseService.getAllCourses();

        // Then
        assertEquals(2, actualResult.size());
    }


    @Test
    void getById() {

    }

    @Test
    void testAddCourse_Success() {
        // Given
        CourseDto mockCourseDto = new CourseDto();
        UserDto mockUserDto = new UserDto();
        mockUserDto.setEmailVerified(true);

        // Mock actions
        when(courseUserClient.getUserById(any())).thenReturn(mockUserDto);
        when(categoryService.ifCategoryExists(any())).thenReturn(true);

        // When
        CourseDto actualResult = mockCourseService.addCourse(mockCourseDto);

        // Then
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void testAddCourse_With_InactiveUser() {
        // Given
        CourseDto mockCourseDto = new CourseDto();
        UserDto mockUserDto = new UserDto();
        mockUserDto.setEmailVerified(false);

        // Mock actions
        when(courseUserClient.getUserById(any())).thenReturn(mockUserDto);

        // Then
        assertThrows(CustomBadRequestException.class, () -> mockCourseService.addCourse(mockCourseDto));
        verify(courseRepository, never()).save(any());
    }

    @Test
    void testAddCourse_With_CategoryNotExists() {
        // Given
        CourseDto mockCourseDto = new CourseDto();
        UserDto mockUserDto = new UserDto();
        mockUserDto.setEmailVerified(true);

        // Mock actions
        when(courseUserClient.getUserById(any())).thenReturn(mockUserDto);
        when(categoryService.ifCategoryExists(any())).thenReturn(false);

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

        mockCourseService.deleteCourse(1L);

        // Then
        verify(courseRepository, times(1)).delete(mockCourse);
    }

    @Test
    void testDeleteCourse_NotFound_Failed() {

        // Mock actions
        when(courseRepository.findById(any())).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class, () -> mockCourseService.deleteCourse(1L));
        verify(courseRepository, never()).delete(any());
    }


}