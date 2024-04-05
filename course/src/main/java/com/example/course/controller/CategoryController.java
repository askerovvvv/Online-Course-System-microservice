package com.example.course.controller;

import com.example.course.models.responsesDto.CategoryResponseDto;
import com.example.course.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get/categories")
    public ResponseEntity<?> findAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }

    @PostMapping("/add/category")
    public ResponseEntity<?> addCategory(@RequestBody CategoryResponseDto categoryData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(categoryData));
    }

    @GetMapping("/get/category/by/id")
    public ResponseEntity<?> findCategoryById(@RequestParam("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryDtoById(id));
    }

    @DeleteMapping("/delete/category/by/id")
    public ResponseEntity<?> deleteCategoryById(@RequestParam("id") int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully.");
    } // TODO: update

}
