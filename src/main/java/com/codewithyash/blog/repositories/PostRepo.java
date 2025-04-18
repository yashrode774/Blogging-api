package com.codewithyash.blog.repositories;

import com.codewithyash.blog.entities.Category;
import com.codewithyash.blog.entities.Post;
import com.codewithyash.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
