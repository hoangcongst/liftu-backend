package me.tektap.liftu.Response.PostResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import me.tektap.liftu.Response.BaseResponse;
import me.tektap.liftu.entity.Post.Post;

public class PostResponse extends BaseResponse {

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Post data;

    public PostResponse(int status, Post mPost) {
        super(status);
        this.data = mPost;
    }
}
