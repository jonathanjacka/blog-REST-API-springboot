package com.springboot.blog.repository;

import com.springboot.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// No need to add @Repository annotation as this is included in extension of JPA Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(long postId);
}
