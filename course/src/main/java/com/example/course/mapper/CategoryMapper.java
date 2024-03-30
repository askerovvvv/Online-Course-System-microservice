package com.example.course.mapper;

import com.example.course.models.Category;
import com.example.course.models.responsesDto.CategoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")

public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CategoryResponseDto categoryResponseDto);
    CategoryResponseDto toCategoryDto(Category category);

    List<CategoryResponseDto> toCategoryDtoList(List<Category> categories);
    List<Category> toCategoryList(List<CategoryResponseDto> categoryResponseDtos);

}
