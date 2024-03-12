package com.example.course.mapper;

import com.example.course.models.Course;
import com.example.course.models.dto.CourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course toCourse(CourseDto courseDto);

}
