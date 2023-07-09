package com.springboot.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Post title requires minimum of 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post description requires minimum of 10 characters")
    private String description;

    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

    private long categoryId;
}
