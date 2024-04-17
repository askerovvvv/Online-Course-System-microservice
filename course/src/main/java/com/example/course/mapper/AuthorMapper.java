package com.example.course.mapper;

import com.example.course.models.Author;
import com.example.course.models.requestsDto.AuthorRequestDto;
import com.example.course.models.responsesDto.AuthorResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    Author toAuthor(AuthorRequestDto authorRequestDto);
    AuthorResponseDto toAuthorResponseDto(Author author);

    List<AuthorResponseDto> toAuthorResponseDtos(List<Author> author);
}
