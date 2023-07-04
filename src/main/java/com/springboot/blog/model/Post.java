package com.springboot.blog.model;

//standard API for accessing Rel dbs in Java (successor to JPA API, which is now deprecated
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


/*
Lombok's dependency handles getters and setters as well as constructors
Normally, you @Data for all getter. setter, toString methods.
However, Model Mapper conflicts with Lombok by using own overwriting of toString - returns comments set as empty
Therefore use only @Getter and @Setter annotations to return comments set correctly
*/
@Getter
@Setter
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true) //Ensures that an operation on parent will include children
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
