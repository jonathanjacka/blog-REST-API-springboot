package com.springboot.blog.service;

import com.springboot.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getAllCommentsByPostId(long postId);
    CommentDto getCommentById(long postId, long commentId);
    CommentDto updateCommentById(long postId, long commentId, CommentDto updatedComment);
    CommentDto deleteCommentById(long postId, long commentId);
}
