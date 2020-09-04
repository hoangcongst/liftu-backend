package me.tektap.liftu.entity;

import lombok.Data;
import me.tektap.liftu.util.VnCharacterUtils;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="comments")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Comment {
    public Comment(String title, String description, String content, byte status) {
        this.title = title;
        this.description = description;
        this.alias = VnCharacterUtils.removeAccent(title).replaceAll("\\s+", "-");;
        this.content = content;
        this.status = status;
    }

    public Comment() {}

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
    @Column(name="description")
    private String description;
    @Column(name="thumbnail")
    private String thumbnail;
    @Column(name="status")
    private byte status;

    @Column(name="created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date created_at;

    @Column(name="updated_at")
    @LastModifiedDate
    private Date updated_at;

    @ManyToOne
    @JoinColumn(name="user_id")
    @CreatedBy
    private User user;
}
