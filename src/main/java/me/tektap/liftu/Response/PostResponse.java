package me.tektap.liftu.Response;

import lombok.Getter;
import me.tektap.liftu.entity.Post;

public class PostResponse extends BaseResponse {

    @Getter
    private Post mPost;

    public PostResponse(int status, Post mPost) {
        super(status);
        this.mPost = mPost;
    }
}
