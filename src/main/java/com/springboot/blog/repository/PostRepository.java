package com.springboot.blog.repository;

import com.springboot.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// No need to add @Repository annotation as this is included in extension of JPA Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
