package com.example.course.mapper;

import com.example.course.models.Category;
import com.example.course.models.Course;
import com.example.course.models.dto.CategoryDto;
import com.example.course.models.dto.CourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")

public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CategoryDto categoryDto);
    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDtoList(List<Category> categories);
    List<Category> toCategoryList(List<CategoryDto> categoryDtos);

}
