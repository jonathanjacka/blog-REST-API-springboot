package com.springboot.blog.repository;

import com.springboot.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

// No need to add @Repository annotation as this is included in extension of JPA Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
