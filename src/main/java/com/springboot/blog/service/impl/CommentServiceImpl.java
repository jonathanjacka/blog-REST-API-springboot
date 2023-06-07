package com.springboot.blog.service.impl;

import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.model.Comment;
import com.springboot.blog.model.Post;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //Indicates service class which allows for injection into other classes though App context
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    // post repository is required to access post, owing to one to many relationship
    private PostRepository postRepository;

    //@Autowired - not required as spring will auto-detect and inject required dependencies,
    //can omit from Spring 3 if a class is a bean and only has one field/constructor
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToComment(commentDto);

        //Retrieve relevant post instance by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        //Set comment to Post instance
        comment.setPost(post);
        //Save comment instance to database
        Comment savedComment = commentRepository.save(comment);

        return mapToDto(savedComment);
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;
    }

    private Comment mapToComment(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        return comment;
    }
}
