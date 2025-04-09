package com.codewithyash.blog;

import com.codewithyash.blog.entities.Category;
import com.codewithyash.blog.entities.Post;
import com.codewithyash.blog.entities.User;
import com.codewithyash.blog.payloads.CategoryDto;
import com.codewithyash.blog.payloads.PostDto;
import com.codewithyash.blog.payloads.UserDto;
import com.codewithyash.blog.repositories.PostRepo;
import com.codewithyash.blog.services.PostService;
import com.codewithyash.blog.services.impl.PostServiceImpl;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;  // For assertions like assertEquals, assertNotNull
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepo postRepo;

    @Mock
    private ModelMapper modelMapper;

    private Post post1, post2;

    @InjectMocks
    private PostServiceImpl postService;

    private PostDto postDto1, postDto2;
    private User user;
    private Category category;
    private UserDto userDto;
    private CategoryDto categoryDto;

    @BeforeEach
    void setUp() {
        // Create User and Category objects
        user = new User();
        user.setId(1);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("securepassword");
        user.setAbout("Software Developer");

        // Create Category instance
        category = new Category();
        category.setCategoryId(1);
        category.setCategoryName("Tech");
        category.setCategoryDescription("Technology related articles");


        // Creating Post objects using setters
        post1 = new Post();
        post1.setPostId(1);
        post1.setTitle("First Post");
        post1.setContent("Content 1");
        post1.setImageName("image1.jpg");
        post1.setAddedDate(new Date());
        post1.setCategory(category);
        post1.setUser(user);

        userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("John Doe");

        categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryName("Tech");

        post2 = new Post();
        post2.setPostId(2);
        post2.setTitle("Second Post");
        post2.setContent("Content 2");
        post2.setImageName("image2.jpg");
        post2.setAddedDate(new Date());
        post2.setCategory(category);
        post2.setUser(user);

        // Creating PostDto objects for expected results
        // Creating PostDto objects for expected results
        postDto1 = new PostDto();
        postDto1.setPostId(1);
        postDto1.setTitle("First Post");
        postDto1.setContent("Content 1");
        postDto1.setAddedDate(post1.getAddedDate());
        postDto1.setUser(userDto);
        postDto1.setCategory(categoryDto);

        postDto2 = new PostDto();
        postDto2.setPostId(2);
        postDto2.setTitle("Second Post");
        postDto2.setContent("Content 2");
        postDto2.setAddedDate(post2.getAddedDate());
        postDto2.setUser(userDto);
        postDto2.setCategory(categoryDto);
    }

    @Test
    void testGetAllPosts() {
        when(postRepo.findAll()).thenReturn(Arrays.asList(post1, post2));

        when(modelMapper.map(any(User.class), eq(UserDto.class)))
                .thenReturn(new UserDto());

        // Mock ModelMapper behavior
        when(modelMapper.map(post1, PostDto.class)).thenReturn(postDto1);
        when(modelMapper.map(post2, PostDto.class)).thenReturn(postDto2);

        // Call service method
        List<PostDto> result = postService.getAllPosts();

        // Verify and assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(postDto1.getTitle(), result.get(0).getTitle());
        assertEquals(postDto2.getTitle(), result.get(1).getTitle());
        assertEquals(postDto1.getUser().getName(), result.get(0).getUser().getName());
        assertEquals(postDto2.getCategory().getCategoryName(), result.get(1).getCategory().getCategoryName());

        // Verify interactions
        verify(postRepo, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(Post.class), eq(PostDto.class));
        verify(modelMapper, times(2)).map(any(User.class), eq(UserDto.class));
        verify(modelMapper, times(2)).map(any(Category.class), eq(CategoryDto.class));
    }
}
