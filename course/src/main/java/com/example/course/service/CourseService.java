package com.example.course.service;

import com.example.course.models.Course;
import com.example.course.models.responsesDto.CourseResponseDto;
import com.example.course.models.requestsDto.CourseRequestDto;

import java.util.List;

public interface CourseService {
    List<CourseResponseDto> findAllCourses();
    CourseResponseDto addCourse(CourseRequestDto courseData);
    void addAuthorToCourse(Long authorId, Long courseId);
    void deleteCourseById(Long courseId);
    CourseResponseDto findCourseDtoById(Long courseId);
    Course findCourseById(Long courseId);
    boolean courseExistsById(Long courseId);
}
