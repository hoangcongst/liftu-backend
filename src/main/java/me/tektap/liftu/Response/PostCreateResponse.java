package me.tektap.liftu.Response;

import lombok.Getter;
import me.tektap.liftu.entity.Post;

public class PostCreateResponse extends BaseResponse {

    @Getter
    private Post mPost;

    public PostCreateResponse(int status, Post mPost) {
        super(status);
        this.mPost = mPost;
    }
}
