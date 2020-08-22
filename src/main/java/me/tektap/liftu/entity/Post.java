package me.tektap.liftu.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="posts")
@Data
public class Post {
    private static final int PUBLISH = 0;
    private static final int DRAFT = 1;
    private static final int PRIVATE = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="title")
    private String title;
    @Column(name="alias")
    private String alias;
    @Column(name="content")
    private String content;
    @Column(name="status")
    private byte status;
    @Column(name="created_at")
    private Date created_at;
    @Column(name="updated_at")
    private Date updated_at;

    @ManyToOne(cascade = {CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name="user_id")
    private User user;
}
