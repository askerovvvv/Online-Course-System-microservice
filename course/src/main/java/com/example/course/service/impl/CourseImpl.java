package com.example.course.service.impl;

import com.example.course.client.CourseUserClient;
import com.example.course.exceptions.CustomBadRequestException;
import com.example.course.exceptions.HttpRequestException;
import com.example.course.exceptions.NotFoundException;
import com.example.course.mapper.CourseMapper;
import com.example.course.models.Author;
import com.example.course.models.Category;
import com.example.course.models.Course;
import com.example.course.models.dto.CourseDto;
import com.example.course.models.dto.AuthorDto;
import com.example.course.repository.CourseRepository;
import com.example.course.service.AuthorService;
import com.example.course.service.CategoryService;
import com.example.course.service.CourseService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CategoryService categoryService;
    private final CourseUserClient courseUserClient;
    private final AuthorService authorService;


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
        // TODO:  в запросе передать Principal principal
        try {
            AuthorDto authorDto = courseUserClient.getUserById(courseData.getTeacherId());
            if (!authorDto.getEmailVerified()) {
                throw new CustomBadRequestException("The user you specified as a teacher is inactive!");
            }

            Category category = categoryService.getCategoryById(courseData.getCategoryId());
            Author author = authorService.findAuthorByEmail(authorDto);
            Course course = createCourse(courseData, author, category);

            courseRepository.save(course);
        } catch (FeignException e) {
            // TODO: обработать нормально
            throw new HttpRequestException(e.getMessage() + " в сервис auth");
        }

        return courseData;
    }

    private Course createCourse(CourseDto courseData, Author author, Category category) {
        Course course = CourseMapper.INSTANCE.toCourse(courseData);
        course.setAuthors(new ArrayList<>(List.of(author)));
        course.setCategory(category);

        return course;
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found!"));

        courseRepository.delete(course);
    }

    // TODO: update TEACHER logic
}
