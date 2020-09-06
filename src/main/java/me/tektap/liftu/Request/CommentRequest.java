package me.tektap.liftu.Request;

import com.sun.istack.NotNull;
import lombok.Data;
import me.tektap.liftu.entity.Comment;

import java.io.Serializable;

@Data
public class CommentRequest implements Serializable {

	private static final long serialVersionUID = -5616176897013108345L;

	private long post_id;
	private long parent_comment_id;
	@NotNull
	private String content;

	public CommentRequest(long postId, String content, long parentCommentId) {
		this.post_id = postId;
		this.content = content;
		this.parent_comment_id = parentCommentId;
	}
}
