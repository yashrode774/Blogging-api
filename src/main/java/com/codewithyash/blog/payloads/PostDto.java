package com.codewithyash.blog.payloads;

import com.codewithyash.blog.entities.Category;
import com.codewithyash.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer postId;

    private String title;

    private String content;

    private Date addedDate;

    private UserDto user;

    private CategoryDto category;
}
