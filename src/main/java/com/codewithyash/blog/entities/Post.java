package com.codewithyash.blog.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "post_title", length = 100)
    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private User user;

}
