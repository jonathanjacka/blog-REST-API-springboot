package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//Lombok package adds all getters and setters
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Post Response Data Transfer Object Model Information - returned on successful GET ALL POSTS request"
)
public class PostResponse {
    @Schema(
            description = "List containing all posts as Post Dtos"
    )
    private List<PostDto> content;
    @Schema(
            description = "(Pagination) Indicates current page number - default will be first page"
    )
    private int pageNo;
    @Schema(
            description = "(Pagination) Indicates number of entries per page returned - default is 10"
    )
    private int pageSize;
    @Schema(
            description = "(Pagination) Indicates total number of content items (posts)"
    )
    private long totalElements;
    @Schema(
            description = "(Pagination) Indicates total number of pages available as per content length and page size"
    )
    private int totalPages;
    @Schema(
            description = "(Pagination) Indicates if current page is final"
    )
    private boolean last;
}
