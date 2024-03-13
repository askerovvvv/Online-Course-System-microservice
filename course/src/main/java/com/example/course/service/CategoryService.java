package com.example.course.service;

import com.example.course.models.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryData);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(int id);
    void updateCategory(int id, CategoryDto categoryData);
    void deleteCategory(int id);

    boolean ifCategoryExists(String name);
    CategoryDto getCategoryByName(String name);
}
