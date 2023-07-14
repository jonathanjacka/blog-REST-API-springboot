package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Tag(
        name = "CRUD routes for Comment Resource - REST API"
)
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(
            summary = "Get all Comments by Post Id REST API",
            description = "Returns a list of comments that share the given post Id as a foreign key. Throws an error if a post with Id is not found."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable(value = "postId") long postId){
        return ResponseEntity.ok(commentService.getAllCommentsByPostId(postId));
    }

    @Operation(
            summary = "Get Comment by Id REST API",
            description = "Returns a single comment by ID. Throws an error if either post, or comment with Id is not found."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public  ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentId){
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }

    @Operation(
            summary = "Create Comment REST API",
            description = "Creates a new comment. Requires that all fields are sent in request body. Must be associated with valid post Id as a foreign key."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 200 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authorization"
    )
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId, @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update Comment by Id REST API",
            description = "Updates single comment by Id. Requires that all fields are sent in request body. Throws an error if either post with Id, or comment with Id is not found."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authorization"
    )
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentId, @Valid @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateCommentById(postId, commentId, commentDto));
    }

    @Operation(
            summary = "Delete Comment by Id REST API",
            description = "Removes single comment from database by Id. Throws an error if either post, or comment with Id is not found."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authorization"
    )
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> deleteCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentId){
        return ResponseEntity.ok(commentService.deleteCommentById(postId, commentId));
    }
}
