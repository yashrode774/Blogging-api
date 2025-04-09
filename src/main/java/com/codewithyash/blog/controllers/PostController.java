package com.codewithyash.blog.controllers;

import com.codewithyash.blog.payloads.PostDto;
import com.codewithyash.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDto createdPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }

    // Get all posts by user id.
    @GetMapping("user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getByUser(@PathVariable Integer userId) {
        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // Get all posts by user id.
    @GetMapping("category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getByCategory(@PathVariable Integer categoryId) {
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // Get all posts.
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getPosts() {
        List<PostDto> posts = this.postService.getAllPosts();
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // Get post by id.
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto post = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(post, HttpStatus.OK);
    }
}
