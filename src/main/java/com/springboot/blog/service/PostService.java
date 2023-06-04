package com.springboot.blog.service;

import com.springboot.blog.payload.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPosts(int pageNo, int pageSize);
    PostDto getPostById(long id);
    PostDto createPost(PostDto postDto);
    PostDto updatePostById(PostDto postDto, long id);
    PostDto deletePostById(long id);
}
