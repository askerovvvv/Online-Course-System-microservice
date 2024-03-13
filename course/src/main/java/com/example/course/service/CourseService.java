package com.example.course.service;

import com.example.course.models.dto.CourseDto;

import java.util.List;

public interface CourseService {
    CourseDto addCourse(CourseDto courseData);
    List<CourseDto> getAllCourses();
    CourseDto getCourseById(Long id);
    void deleteCourse(Long id);
}
