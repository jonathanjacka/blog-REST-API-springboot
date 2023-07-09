package com.springboot.blog.service;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(long id);
    PostDto createPost(PostDto postDto);
    PostDto updatePostById(PostDto postDto, long id);
    PostDto deletePostById(long id);

    List<PostDto> getPostsByCategory(long categoryId);
}
