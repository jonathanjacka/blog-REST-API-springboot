package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
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
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    //GET single blog post by id API
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //POST blog post REST API
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

}
