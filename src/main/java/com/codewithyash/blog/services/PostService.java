package com.codewithyash.blog.services;

import com.codewithyash.blog.entities.Post;
import com.codewithyash.blog.payloads.PostDto;

import java.util.List;

public interface PostService {

    // Create.
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    // Update.
    Post updatePost(PostDto postDto, Integer postId);
    // Delete.
    void deletePost(Integer PostId);
    // GetAll
    List<PostDto> getAllPosts();
    // Get by id.
    PostDto getPostById(Integer postId);

    // Get all posts by category.
    List<PostDto> getPostsByCategory(Integer categoryId);
    // Get all posts by user.
    List<PostDto> getPostsByUser(Integer userId);
    // Get all posts by Keyword.
    List<Post> getPostByKeyword(String KeyWord);
 }
