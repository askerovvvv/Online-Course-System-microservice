package com.example.course.service;

import com.example.course.models.Category;
import com.example.course.models.responsesDto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto addCategory(CategoryResponseDto categoryData);
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto getCategoryDtoById(int id);
    Category getCategoryById(int id);
    void updateCategory(int id, CategoryResponseDto categoryData);
    void deleteCategory(int id);

    boolean ifCategoryExists(String name);
    CategoryResponseDto getCategoryByName(String name);
}
