package me.tektap.liftu.Response;

import lombok.Getter;
import me.tektap.liftu.entity.Post.Post;

public class PostCreateResponse extends BaseResponse {

    @Getter
    private final Post data;

    public PostCreateResponse(int status, Post mPost) {
        super(status);
        this.data = mPost;
    }
}
