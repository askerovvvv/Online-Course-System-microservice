package com.example.course.service;

import com.example.course.models.dto.CategoryDto;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryData);
    boolean ifCategoryExists(String name);
}
