package me.tektap.liftu.Response;

import lombok.Getter;
import me.tektap.liftu.entity.Post.Post;
import me.tektap.liftu.entity.Post.PostAtIndex;
import org.springframework.data.domain.Page;

public class PostIndexResponse extends BaseResponse {

    @Getter
    private final Page<PostAtIndex> data;

    public PostIndexResponse(int status, Page<PostAtIndex> pagePosts) {
        super(status);
        this.data = pagePosts;
    }
}
