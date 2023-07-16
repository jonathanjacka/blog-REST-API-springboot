package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
//swagger
@Tag(
        name = "CRUD routes for Post Resource - REST API"
)
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //GET all blog posts API
    //Swagger docs req
    @Operation(
            summary = "Get all Posts REST API",
            description = "Returns a list of all blog posts from database. Allows for both sorting and pagination."  +
                    "\n" +
                    "Default sorting is by post Id.  Default pagination is set to pageNo = 0 (first page) and pageSize = 10. For further reference on pagination and sorting: see examples below.\n" +
                    "\n" +
                    "Returns an object that includes the following:\n" +
                    "\n" +
                    "content - a list of all posts\n" +
                    "\n" +
                    "pageNo - the current page number (defaults to 0; first page)\n" +
                    "\n" +
                    "pageSize - number of entries per page (defaults to 10)\n" +
                    "\n" +
                    "totalElements - total number of post entries; length of content list\n" +
                    "\n" +
                    "totalPages - total number of pages given current pagination settings\n" +
                    "\n" +
                    "last - boolean value indicating if last page has been reached with current pagination settings"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value ="sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return ResponseEntity.ok(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir));
    }

    //GET single blog post by id REST API
    @Operation(
            summary = "Get Post by Id REST API",
            description = "Returns single post that matches id. If no post with the matching id is found, and error is thrown - return message will indicate that given post was not found."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //GET posts by category REST API
    @GetMapping("/categories/{id}")
    //Swagger docs req
    @Operation(
            summary = "Get Posts by Category Id REST API",
            description = "Returns a list of posts that matches the given category id. If no category with the matching id is found, and error is thrown - return message will indicate that given category Id was not found."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(value = "id") long categoryId){
        List<PostDto> posts = postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(posts);
    }

    //POST blog post REST API
    //Swagger docs req
    @Operation(
            summary = "Create Post REST API",
            description = "Creates a new blog post. Requires unique title. Must also include description and content."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authorization"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //PUT blog post by id REST API
    //Swagger docs req
    @Operation(
            summary = "Update Post by Id REST API",
            description = "Updates single post by ID. Requires that all fields are sent in request body. Throws an error if post with Id is not found."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authorization"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.updatePostById(postDto, id));
    }

    //DELETE blog post by id REST API
    //Swagger docs req
    @Operation(
            summary = "Delete Post by Id REST API",
            description = "Removes post of given id from the database. Returns the deleted post. Error is thrown if given Id does not match any post in database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authorization"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<PostDto> deletePostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.deletePostById(id));
    }

}
