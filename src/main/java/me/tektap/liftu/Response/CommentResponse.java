package me.tektap.liftu.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import me.tektap.liftu.entity.Comment;
import me.tektap.liftu.entity.Post.Post;

public class CommentResponse extends BaseResponse {

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Comment data;
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message = null;

    public CommentResponse(int status, Comment comment) {
        super(status);
        this.data = comment;
    }
}
