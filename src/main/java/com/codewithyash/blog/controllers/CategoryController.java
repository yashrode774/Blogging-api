package com.codewithyash.blog.controllers;

import com.codewithyash.blog.payloads.ApiResponse;
import com.codewithyash.blog.payloads.CategoryDto;
import com.codewithyash.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Create.
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createdCat = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createdCat,HttpStatus.CREATED);
    }
    // Update.
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId) {
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, catId);
        return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
    }
    // Delete.
    @DeleteMapping("/")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId) {
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("category deletd successfully", true), HttpStatus.OK);
    }
    // Get.
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId) {
        CategoryDto cat = this.categoryService.getCategory(catId);
        return new ResponseEntity<CategoryDto>(cat, HttpStatus.OK);
    }
    // Get-all.
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> cat = this.categoryService.getCategories();
        return ResponseEntity.ok(cat);
    }
}
