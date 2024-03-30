package com.example.course.service.impl;

import com.example.course.client.CourseUserClient;
import com.example.course.exceptions.CustomBadRequestException;
import com.example.course.exceptions.HttpRequestException;
import com.example.course.exceptions.NotFoundException;
import com.example.course.mapper.CourseMapper;
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
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CategoryService categoryService;
    private final CourseUserClient courseUserClient;
    private final AuthorService authorService;

    // TODO: send email(something added/ deleted to all authors of courses ot students)

    @Override
    public List<CourseResponseDto> findAllCourses() {
        return CourseMapper.INSTANCE.toCourseDtoList(courseRepository.findAll());
    }

    @Override
    public CourseResponseDto addCourse(CourseRequestDto courseData) {
        // TODO:  в запросе передать Principal principal
        try {
            AuthorDto authorDto = courseUserClient.getUserById(courseData.getAuthorId());
            if (!authorDto.getEmailVerified()) {
                throw new CustomBadRequestException("The user you specified as a teacher is inactive!");
            }

            Category category = categoryService.getCategoryById(courseData.getCategoryId());
            Author author = authorService.findAuthorByEmailOrCreate(authorDto);
            Course course = createCourse(courseData, author, category);

            courseRepository.save(course);
            return CourseMapper.INSTANCE.toCourseDto(course);
        } catch (FeignException e) {
            // TODO: обработать нормально
            throw new HttpRequestException(e.getMessage() + " в сервис auth");
        }

    }

    private Course createCourse(CourseRequestDto courseData, Author author, Category category) {
        Course course = CourseMapper.INSTANCE.toCourse(courseData);
        course.setAuthors(new ArrayList<>(List.of(author)));
        course.setCategory(category);

        return course;
    }

    @Override
    public void addAuthorToCourse(Long authorId, Long courseId) {
        Author author = authorService.findAuthorById(authorId);
        Course course = findCourseById(courseId);

        // TODO: check if Principal in list below
//        course.getAuthors();
        course.getAuthors().add(author);

    }

    @Override
    public void deleteCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found!"));

        courseRepository.delete(course);
    }

    @Override
    public CourseResponseDto findCourseDtoById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found!"));
        return CourseMapper.INSTANCE.toCourseDto(course);
    }

    @Override
    public Course findCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found!"));
    }

    @Override
    public boolean courseExistsById(Long courseId) {
        return courseRepository.existsById(courseId);
    }
}
