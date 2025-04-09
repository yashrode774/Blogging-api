package com.codewithyash.blog.services.impl;

import com.codewithyash.blog.entities.Category;
import com.codewithyash.blog.exception.ResourceNotFoundException;
import com.codewithyash.blog.payloads.CategoryDto;
import com.codewithyash.blog.repositories.CategoryRepo;
import com.codewithyash.blog.services.CategoryService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category addedCat = this.categoryRepo.save(cat);
        return modelMapper.map(addedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category cat =  this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", id));
        cat.setCategoryName(categoryDto.getCategoryName());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCat = this.categoryRepo.save(cat);
        return modelMapper.map(updatedCat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer id) {
        Category cat =this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", id));
        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer id) {
        Category cat = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", id));
        return modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category category: categories) {
            categoryDtos.add(modelMapper.map(category, CategoryDto.class));
        }
        return categoryDtos;
    }
}
