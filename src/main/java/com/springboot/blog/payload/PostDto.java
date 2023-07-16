package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
//Swagger Docs
@Schema(
        description = "Post Data Transfer Object Model Information"
)
public class PostDto {
    private Long id;

    @Schema(
            description = "Blog Post Title"
    )
    @NotEmpty
    @Size(min = 2, message = "Post title requires minimum of 2 characters")
    private String title;

    @Schema(
            description = "Blog Post Description"
    )
    @NotEmpty
    @Size(min = 10, message = "Post description requires minimum of 10 characters")
    private String description;

    @Schema(
            description = "Blog Post Content"
    )
    @NotEmpty
    private String content;

    @Schema(
            description = "Blog Post Comments"
    )
    private Set<CommentDto> comments;

    @Schema(
            description = "Blog Post Category Reference Id"
    )
    private long categoryId;
}
