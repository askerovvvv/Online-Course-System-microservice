package com.example.course.controller;

import com.example.course.models.dto.CourseDto;
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
    public ResponseEntity<?> addCourse(@RequestBody CourseDto courseData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(courseData));
    }

    @GetMapping("/get/course")
    public ResponseEntity<?> findCourseById(@RequestParam("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findCourseDtoById(id));
    }

    @DeleteMapping("/delete/course")
    public ResponseEntity<?> deleteCourseById(@RequestParam("id") Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.status(200).build();
    } // TODO: update

}
