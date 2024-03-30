package com.example.course.mapper;

import com.example.course.models.Author;
import com.example.course.models.Course;
import com.example.course.models.dto.AuthorDto;
import com.example.course.models.dto.CourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    Author toAuthor(AuthorDto authorDto);
    AuthorDto toAuthorDto(Author author);
}
