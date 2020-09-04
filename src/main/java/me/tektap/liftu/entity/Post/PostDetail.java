package me.tektap.liftu.entity.Post;

import org.springframework.beans.factory.annotation.Value;

public interface PostDetail {
    String getId();
    String getTitle();
    String getAlias();
    String getDescription();
    String getThumbnail();
    String getContent();
    @Value("#{target.created_at}")
    String getCreatedAt();
    @Value("#{target.user.username}")
    String getUsername();
}
