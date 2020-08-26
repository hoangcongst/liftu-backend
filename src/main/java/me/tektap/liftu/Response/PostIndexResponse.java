package me.tektap.liftu.Response;

import lombok.Getter;
import me.tektap.liftu.entity.Post;
import org.springframework.data.domain.Page;

public class PostIndexResponse extends BaseResponse {

    @Getter
    private Page<Post> pagePosts;

    public PostIndexResponse(int status, Page<Post> pagePosts) {
        super(status);
        this.pagePosts = pagePosts;
    }
}
