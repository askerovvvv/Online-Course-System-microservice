package com.example.course.service.impl;

import com.example.course.exceptions.NotFoundException;
import com.example.course.models.Category;
import com.example.course.models.dto.CategoryDto;
import com.example.course.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryServiceImpl mockCategoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockCategoryService = new CategoryServiceImpl(
                categoryRepository
        );
    }

    @Test
    void testAddCategory() {
        // Given
        Category mockCategory = new Category();

        // Mock actions
        when(categoryRepository.save(any(Category.class))).thenReturn(mockCategory);

        // When
        CategoryDto actualResult = mockCategoryService.addCategory(new CategoryDto());

        // Then
        verify(categoryRepository, times(1)).save(any(Category.class));
        assertInstanceOf(CategoryDto.class, actualResult);
    }

    @Test
    void testGetAllCategories() {
        // Given
        Category mockCategory1 = new Category();
        Category mockCategory2 = new Category();
        List<Category> mockCategories = new ArrayList<>(
                List.of(mockCategory2, mockCategory2)
        );

        // Mock actions
        when(categoryRepository.findAll()).thenReturn(mockCategories);


        // When
        List<CategoryDto> actualResult = mockCategoryService.getAllCategories();

        // Then
        assertEquals(mockCategories.size(), actualResult.size());
    }

    @Test
    void testGetCategoryById_Success() {
        // Given
        Category mockCategory = new Category();

        // Mock action
        when(categoryRepository.findById(any())).thenReturn(Optional.of(mockCategory));

        // When
        CategoryDto actualResult = mockCategoryService.getCategoryDtoById(1);

        // Then
        assertNotNull(actualResult);
        assertInstanceOf(CategoryDto.class, actualResult);
    }

    @Test
    void testGetCategoryById_Failed() {
        // Mock action
        when(categoryRepository.findById(any())).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class, () -> mockCategoryService.getCategoryDtoById(1));
    }

    @Test
    void updateCategory() {

    }

    @Test
    void testDeleteCategory_Success() {
        // Given
        Category mockCategory = new Category();

        // Mock actions
        when(categoryRepository.findById(any())).thenReturn(Optional.of(mockCategory));

        // When
        mockCategoryService.deleteCategory(1);

        // Then
        verify(categoryRepository, times(1)).delete(mockCategory);
    }

    @Test
    void testDeleteCategory_NotFound_Failed() {
        // Mock actions
        when(categoryRepository.findById(any())).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class, () -> mockCategoryService.deleteCategory(1));
        verify(categoryRepository, never()).delete(any());
    }
}