package com.springboot.blog.service.impl;

import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.model.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.PageRequest.*;

@Service //indicates service class to aid Spring boot auto-detection
public class PostServiceImpl implements PostService {

    //Constructor-based dependency injection
    private PostRepository postRepository;

    //@Autowired - can omit from Spring 3 if a class is a bean and only has one field/constructor
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        /* PAGINATION AND SORTING */
        //Arrange Sort by ascending or decending
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        //Create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        //Get posts via generic Page object
        Page<Post> posts = postRepository.findAll(pageable);
        //Get content from Page instance to List of posts using getContent() method
        List<Post> paginatedPosts = posts.getContent();

        List<PostDto> content = paginatedPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        //Post Response object allows for specific format of response
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;

    }

    @Override
    public PostDto getPostById(long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public PostDto deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        PostDto deletedPost = mapToDto(post);

        postRepository.delete(post);
        return deletedPost;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //Convert Dto to model/entity
        Post post = mapToPost(postDto);
        Post newPost = postRepository.save(post);

        //Convert model instance back to Dto for return to controller
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
    }

    private PostDto mapToDto(Post post){
        PostDto newPostDto = new PostDto();
        newPostDto.setId(post.getId());
        newPostDto.setTitle(post.getTitle());
        newPostDto.setDescription(post.getDescription());
        newPostDto.setContent(post.getContent());

        return newPostDto;
    }

    private Post mapToPost(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }
}
