package com.example.course.service.impl;

import com.example.course.client.CourseUserClient;
import com.example.course.exceptions.CustomBadRequestException;
import com.example.course.exceptions.NotFoundException;
import com.example.course.models.Author;
import com.example.course.models.Category;
import com.example.course.models.Course;
import com.example.course.models.responsesDto.CourseResponseDto;
import com.example.course.models.responsesDto.AuthorDto;
import com.example.course.models.requestsDto.CourseRequestDto;
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

class CourseServiceImplTest {

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
        mockCourseService = new CourseServiceImpl(
                courseRepository,
                categoryService,
                courseUserClient,
                authorService
        );
    }

    @Test
    void testGetAllCourses() {
        // Given
        Category category = new Category();

        Course course1 = new Course();
        Course course2 = new Course();
        course1.setCategory(category);
        course2.setCategory(category);

        List<Course> mockCourses = new ArrayList<>(List.of(course1, course2));

        // Mock actions
        when(courseRepository.findAll()).thenReturn(mockCourses);

        // When
        List<CourseResponseDto> actualResult = mockCourseService.findAllCourses();

        // Then
        assertEquals(2, actualResult.size());
    }


    @Test
    void testGetCourseById_Success() {
        // Given
        Category category = new Category();
        Course mockCourse = new Course();
        mockCourse.setCategory(category);
        // Mock action
        when(courseRepository.findById(any())).thenReturn(Optional.of(mockCourse));

        // When
        CourseResponseDto actualResult = mockCourseService.findCourseDtoById(1L);

        // Then
        assertNotNull(actualResult);
        assertInstanceOf(CourseResponseDto.class, actualResult);
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
        CourseRequestDto mockCourseRequestDto = new CourseRequestDto();
        mockCourseRequestDto.setCategoryId(2);
        mockCourseRequestDto.setAuthorId(1L);

        AuthorDto mockAuthorDto = new AuthorDto();
        mockAuthorDto.setEmailVerified(true);

        Category mockCategory = new Category();
        mockCategory.setId(2);

        Course mockCourse = new Course();
        mockCourse.setCategory(mockCategory);

        // Mock actions
        when(courseUserClient.getUserById(1L)).thenReturn(mockAuthorDto);
        when(categoryService.getCategoryById(2)).thenReturn(mockCategory);
        when(authorService.findAuthorByEmailOrCreate(mockAuthorDto)).thenReturn(new Author());
        when(courseRepository.save(any())).thenReturn(mockCourse);
//        when(courseRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        CourseResponseDto actualResult = mockCourseService.addCourse(mockCourseRequestDto);

        // Then
        assertNotNull(actualResult);
        assertEquals(mockCourseRequestDto.getCategoryId(), actualResult.getCategoryId());
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void testAddCourse_With_InactiveUser() {
        // Given
        CourseRequestDto mockCourseRequestDto = new CourseRequestDto();
        AuthorDto mockAuthorDto = new AuthorDto();
        mockAuthorDto.setEmailVerified(false);

        // Mock actions
        when(courseUserClient.getUserById(any())).thenReturn(mockAuthorDto);

        // Then
        assertThrows(CustomBadRequestException.class, () -> mockCourseService.addCourse(mockCourseRequestDto));
        verify(courseRepository, never()).save(any());
    }

    @Test
    void testAddCourse_With_CategoryNotExists() {
        // Given
        CourseRequestDto mockCourseRequestDto = new CourseRequestDto();
        mockCourseRequestDto.setCategoryId(2);
        AuthorDto mockAuthorDto = new AuthorDto();
        mockAuthorDto.setEmailVerified(true);

        // Mock actions
        when(courseUserClient.getUserById(any())).thenReturn(mockAuthorDto);
        when(categoryService.getCategoryById(any(Integer.class))).thenThrow(NotFoundException.class);

        // Then
        assertThrows(NotFoundException.class, () -> mockCourseService.addCourse(mockCourseRequestDto));
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