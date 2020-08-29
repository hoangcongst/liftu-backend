package me.tektap.liftu.entity.Post;

import org.springframework.beans.factory.annotation.Value;

public interface PostAtIndex {
    String getId();
    String getTitle();
    String getAlias();
    String getDescription();
    String getThumbnail();
    @Value("#{target.created_at}")
    String getCreatedAt();
    @Value("#{target.user.username}")
    String getUsername();
}
