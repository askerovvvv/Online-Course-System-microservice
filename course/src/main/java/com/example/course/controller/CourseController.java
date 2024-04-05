package com.example.course.controller;

import com.example.course.models.requestsDto.CourseRequestDto;
import com.example.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get/courses")
    public ResponseEntity<?> findAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAllCourses());
    }

    @PostMapping("/add/course")
    public ResponseEntity<?> addCourse(@RequestBody CourseRequestDto courseData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(courseData));
    }

    @GetMapping("/get/course/by/id")
    public ResponseEntity<?> findCourseById(@RequestParam("courseId") Long courseId) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findCourseDtoById(courseId));
    }

    @DeleteMapping("/delete/course")
    public ResponseEntity<?> deleteCourseById(@RequestParam("courseId") Long courseId) {
        courseService.deleteCourseById(courseId);
        return ResponseEntity.status(200).build();
    } // TODO: update

}
