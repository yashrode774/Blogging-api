package com.codewithyash.blog.services.impl;


import com.codewithyash.blog.entities.Category;
import com.codewithyash.blog.entities.Post;
import com.codewithyash.blog.entities.User;
import com.codewithyash.blog.exception.ResourceNotFoundException;
import com.codewithyash.blog.payloads.PostDto;
import com.codewithyash.blog.repositories.CategoryRepo;
import com.codewithyash.blog.repositories.PostRepo;
import com.codewithyash.blog.repositories.UserRepo;
import com.codewithyash.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        Post newPost = postRepo.save(post);
        return modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer PostId) {

    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = this.postRepo.findAll();
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post: posts) {
            postDtos.add(modelMapper.map(post, PostDto.class));
        }
        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post: posts) {
            postDtos.add(modelMapper.map(post, PostDto.class));
        }
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post: posts) {
            postDtos.add(modelMapper.map(post, PostDto.class));
        }
        return postDtos;
    }

    @Override
    public List<Post> getPostByKeyword(String KeyWord) {
        return List.of();
    }
}
