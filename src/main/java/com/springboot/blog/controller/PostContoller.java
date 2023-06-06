package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostContoller {

    private PostService postService;

    public PostContoller(PostService postService) {
        this.postService = postService;
    }

    //GET all blog posts API
    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value ="sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        return ResponseEntity.ok(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir));
    }

    //GET single blog post by id REST API
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //POST blog post REST API
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //PUT blog post by id REST API
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.updatePostById(postDto, id));
    }

    //DELETE blog post by id REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<PostDto> deletePostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.deletePostById(id));
    }

}
