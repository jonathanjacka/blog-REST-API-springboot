package com.springboot.blog.service.impl;

import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.model.Comment;
import com.springboot.blog.model.Post;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //Indicates service class which allows for injection into other classes though App context
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    // post repository is required to access post, owing to one to many relationship
    private PostRepository postRepository;
    //moodel mapper
    private ModelMapper mapper;

    //@Autowired - not required as spring will auto-detect and inject required dependencies,
    //can omit from Spring 3 if a class is a bean and only has one field/constructor
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {
        //Retrieve post instance by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        //retrieve comments by post id
        List<Comment> comments = commentRepository.findByPostId(postId);
        //return list of comments converted to Dtos
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        //Retrieve post instance by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        //Retrieve comment instance by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        //Confirm comment is associated with id
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment is not associated with Post Id");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateCommentById(long postId, long commentId, CommentDto updatedComment) {
        //Retrieve post instance by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        //Retrieve comment instance by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        //Confirm comment is associated with id
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment is not associated with Post Id");
        }

        //update comment that was retrieved
        comment.setName(updatedComment.getName());
        comment.setEmail(updatedComment.getEmail());
        comment.setBody(updatedComment.getBody());

        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public CommentDto deleteCommentById(long postId, long commentId) {
        //Retrieve post instance by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        //Retrieve comment instance by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        //Confirm comment is associated with id
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment is not associated with Post Id");
        }

        CommentDto deletedComment = mapToDto(comment);
        commentRepository.delete(comment);

        return deletedComment;
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
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
        return commentDto;
    }

    private Comment mapToComment(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
        return comment;
    }
}
