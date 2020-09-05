package me.tektap.liftu.entity;

import lombok.Data;
import me.tektap.liftu.entity.Post.Post;
import me.tektap.liftu.util.VnCharacterUtils;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="comments")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Comment {
    public static final byte VISIBLE = 0;
    public static final byte HIDDEN = 1;

    public Comment(long postId, String content, long parentCommentId) {
        this.postId = postId;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.status = Comment.VISIBLE;
    }

    public Comment() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="content")
    private String content;
    @Column(name="post_id")
    private long postId;
    @Column(name="parent_comment_id")
    private long parentCommentId;
    @Column(name="status")
    private byte status;
    @Column(name="number_child_comment")
    private int numberChildComment;
    @Column(name="created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date created_at;

    @Column(name="updated_at")
    @LastModifiedDate
    private Date updated_at;

    @OneToOne
    @JoinColumn(name="user_id")
    @CreatedBy
    private User user;
}
