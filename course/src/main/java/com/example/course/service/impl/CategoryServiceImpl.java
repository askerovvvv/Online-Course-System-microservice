package com.example.course.service.impl;

import com.example.course.exceptions.NotFoundException;
import com.example.course.models.Category;
import com.example.course.models.dto.CategoryDto;
import com.example.course.repository.CategoryRepository;
import com.example.course.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(CategoryDto categoryData) {
        Category category = new Category();
        category.setName(category.getName());
        categoryRepository.save(category);
        return categoryData;
    }

    @Override
    public boolean ifCategoryExists(String name) {
        return categoryRepository.findByName(name).isPresent();
    }
}
