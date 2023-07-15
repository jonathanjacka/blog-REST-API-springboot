package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
//Swagger Docs
@Schema(
        description = "Comment Data Transfer Object Model Information"
)
public class CommentDto {
    private long id;

    @NotEmpty(message = "Name should not be empty")
    //Swagger Docs
    @Schema(
            description = "Name of commentator"
    )
    private String name;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email must be in valid format")
    //Swagger Docs
    @Schema(
            description = "Email address of commentator"
    )
    private String email;

    @NotEmpty(message = "Body of comment should not be empty")
    @Size(min = 10, message = "Content length must be 10 characters of greater")
    //Swagger Docs
    @Schema(
            description = "Comment content/body"
    )
    private String body;
}
