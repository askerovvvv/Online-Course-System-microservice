package com.example.course.mapper;

import com.example.course.models.Course;
import com.example.course.models.dto.CourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course toCourse(CourseDto courseDto);
    CourseDto toCourseDto(Course course);

    List<CourseDto> toCourseDtoList(List<Course> courses);
    List<Course> toCourseList(List<CourseDto> courseDtos);

}
