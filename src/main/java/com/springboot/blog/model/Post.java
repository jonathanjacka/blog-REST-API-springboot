package com.springboot.blog.model;

//standard API for accessing Rel dbs in Java (successor to JPA API, which is now deprecated
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//Handles getters and setters as well as constructors
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
//if not specified, JPA will use class name for table name
@Table(
        name = "posts",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {

    //Primary key specification
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;
}
