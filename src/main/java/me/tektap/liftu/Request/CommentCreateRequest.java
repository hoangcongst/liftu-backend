package me.tektap.liftu.Request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CommentCreateRequest implements Serializable {

	private static final long serialVersionUID = -5616176897013108345L;

	@NotNull(message = "Please enter PostId!")
	@PositiveOrZero
	private long post_id;
	@NotNull
	@PositiveOrZero
	private long parent_comment_id;
	@NotNull
	@Size(min = 5, max = 65000)
	private String content;

	public CommentCreateRequest(long postId, String content, long parentCommentId) {
		this.post_id = postId;
		this.content = content;
		this.parent_comment_id = parentCommentId;
	}
}
