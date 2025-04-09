package com.codewithyash.blog.services;

import com.codewithyash.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    // Create.
    CategoryDto createCategory(CategoryDto categoryDto);
    // Update.
    CategoryDto updateCategory(CategoryDto categoryDto, Integer id);
    // Delete.
    void deleteCategory(Integer id);
    // Get.
    CategoryDto getCategory(Integer id);
    // Get-all.
    List<CategoryDto> getCategories();
}
