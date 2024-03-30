package com.example.course.service.impl;

import com.example.course.exceptions.CustomBadRequestException;
import com.example.course.exceptions.NotFoundException;
import com.example.course.mapper.CategoryMapper;
import com.example.course.models.Category;
import com.example.course.models.responsesDto.CategoryResponseDto;
import com.example.course.repository.CategoryRepository;
import com.example.course.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto addCategory(CategoryResponseDto categoryData) {
        if (categoryRepository.existsByName(categoryData.getName())) {
            throw new CustomBadRequestException("Such a category already exists!");
        }
        Category category = new Category();
        category.setName(category.getName());
        categoryRepository.save(category);
        return categoryData;
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return CategoryMapper.INSTANCE.toCategoryDtoList(categoryRepository.findAll());
    }

    @Override
    public CategoryResponseDto getCategoryDtoById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found!"));
        return CategoryMapper.INSTANCE.toCategoryDto(category);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found!"));
    }

    @Override
    public void updateCategory(int id, CategoryResponseDto categoryData) {
        // TODO: update
    }

    @Override
    public void deleteCategory(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found!"));
        categoryRepository.delete(category);
    }

    @Override
    public boolean ifCategoryExists(String name) {
        return categoryRepository.findByName(name).isPresent();
    }

    @Override
    public CategoryResponseDto getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Category not found!"));
        return CategoryMapper.INSTANCE.toCategoryDto(category);
    }
}
