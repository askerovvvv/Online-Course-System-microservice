package com.example.course.service;

import com.example.course.models.Course;
import com.example.course.models.dto.CourseDto;

import java.util.List;

public interface CourseService {
    List<CourseDto> findAllCourses();
    CourseDto addCourse(CourseDto courseData);
    void addAuthorToCourse(Long authorId, Long courseId);
    void deleteCourseById(Long courseId);
    CourseDto findCourseDtoById(Long courseId);
    Course findCourseById(Long courseId);
    boolean courseExistsById(Long courseId);
}
