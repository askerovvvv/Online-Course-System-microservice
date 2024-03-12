package com.example.course.service.impl;

import com.example.course.client.CourseUserClient;
import com.example.course.exceptions.CustomBadRequestException;
import com.example.course.exceptions.HttpRequestException;
import com.example.course.exceptions.NotFoundException;
import com.example.course.mapper.CourseMapper;
import com.example.course.models.Course;
import com.example.course.models.dto.CourseDto;
import com.example.course.models.dto.UserDto;
import com.example.course.repository.CourseRepository;
import com.example.course.service.CategoryService;
import com.example.course.service.CourseService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CategoryService categoryService;
    private final CourseUserClient courseUserClient;


    @Override
    public CourseDto addCourse(CourseDto courseData) {
        try {
            UserDto userDto = courseUserClient.getUserById(courseData.getTeacherId());
            if (!userDto.getEmailVerified()) {
                throw new CustomBadRequestException("The user you specified as a teacher is inactive!");
            }
            Course course = CourseMapper.INSTANCE.toCourse(courseData);
            if (!categoryService.ifCategoryExists(courseData.getCategory())) {
                throw new NotFoundException("Category with this name is not found!");
            };

            courseRepository.save(course);
        } catch (FeignException e) {
            throw new HttpRequestException(e.getMessage());
        }


        return courseData;
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return null;
    }

    @Override
    public CourseDto getById(Long id) {
        return null;
    }
}
