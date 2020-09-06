package me.tektap.liftu.entity.Post;

import org.springframework.beans.factory.annotation.Value;

public interface PostAtIndex {
    long getId();
    String getTitle();
    String getAlias();
    String getDescription();
    String getThumbnail();
    int getNumberComment();
    @Value("#{target.created_at}")
    String getCreatedAt();
    @Value("#{target.user.display_name}")
    String getDisplayName();
}
