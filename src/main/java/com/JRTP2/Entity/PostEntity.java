package com.JRTP2.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Post_Tbl")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    @Lob
    private String content;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    @ManyToOne
    @JoinColumn(name="userId")
    private UserEntity user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<CommentEntity> comments;
}
