package com.example.course.service.impl;

import com.example.course.client.CourseUserClient;
import com.example.course.exceptions.CustomBadRequestException;
import com.example.course.exceptions.HttpRequestException;
import com.example.course.exceptions.NotFoundException;
import com.example.course.mapper.CourseMapper;
import com.example.course.models.Course;
import com.example.course.models.dto.CategoryDto;
import com.example.course.models.dto.CourseDto;
import com.example.course.models.dto.UserDto;
import com.example.course.repository.CourseRepository;
import com.example.course.service.CategoryService;
import com.example.course.service.CourseService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CourseImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CategoryService categoryService;
    private final CourseUserClient courseUserClient;


    @Override
    public List<CourseDto> getAllCourses() {
        return CourseMapper.INSTANCE.toCourseDtoList(courseRepository.findAll());
    }

    @Override
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found!"));
        return CourseMapper.INSTANCE.toCourseDto(course);
    }

    @Override
    public CourseDto addCourse(CourseDto courseData) {
        try {
            UserDto userDto = courseUserClient.getUserById(courseData.getTeacherId());
            if (!userDto.getEmailVerified()) {
                throw new CustomBadRequestException("The user you specified as a teacher is inactive!");
            }

            CategoryDto categoryDto = categoryService.getCategoryById(courseData.getCategoryId());
            Course course = CourseMapper.INSTANCE.toCourse(courseData);

            courseRepository.save(course);
        } catch (FeignException e) {
            // TODO: обработать нормально
            throw new HttpRequestException(e.getMessage() + " в сервис auth");

        }

        return courseData;
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found!"));

        courseRepository.delete(course);
    }

    // TODO: update TEACHER logic
}
