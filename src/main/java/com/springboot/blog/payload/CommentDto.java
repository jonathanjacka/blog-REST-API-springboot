package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email must be in valid format")
    private String email;

    @NotEmpty(message = "Body of comment should not be empty")
    @Size(min = 10, message = "Content length must be 10 characters of greater")
    private String body;
}
