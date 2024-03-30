package com.example.course.mapper;

import com.example.course.models.Category;
import com.example.course.models.Course;
import com.example.course.models.dto.CourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

//    @Mapping(target = "category.id", source = "categoryId")
    Course toCourse(CourseDto courseDto);

    @Mapping(target = "categoryId", source = "category.id")
    CourseDto toCourseDto(Course course);

    List<CourseDto> toCourseDtoList(List<Course> courses);
    List<Course> toCourseList(List<CourseDto> courseDtos);

}
