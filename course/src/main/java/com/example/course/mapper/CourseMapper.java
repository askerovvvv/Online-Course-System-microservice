package com.example.course.mapper;

import com.example.course.models.Author;
import com.example.course.models.Course;
import com.example.course.models.responsesDto.CourseResponseDto;
import com.example.course.models.requestsDto.CourseRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

//    @Mapping(target = "category.id", source = "categoryId")
    Course toCourse(CourseRequestDto courseData);

    @Mapping(target = "authorsId", source = "course", qualifiedByName = "getAuthorsId")
    @Mapping(target = "categoryId", source = "course", qualifiedByName = "getCategoryId")
    CourseResponseDto toCourseDto(Course course);

    List<CourseResponseDto> toCourseDtoList(List<Course> courses);

    List<Course> toCourseList(List<CourseResponseDto> courseRequestDtos);

    @Named("getAuthorsId")
    default List<Long> getAuthorsId(Course course) {
        List<Author> authors = course.getAuthors();
        List<Long> authorsId = new ArrayList<>();

        for (int i = 0; i < authors.size(); i++) {
            authorsId.add(authors.get(i).getId());
        }

        return authorsId;
    }

    @Named("getCategoryId")
    default Integer getCategoryId(Course course) {
        return course.getCategory().getId();
    }

}
