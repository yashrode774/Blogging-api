package com.codewithyash.blog.repositories;

import com.codewithyash.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
