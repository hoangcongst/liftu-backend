package me.tektap.liftu.Request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CommentUpdateRequest implements Serializable {

	private static final long serialVersionUID = -5616176897013108345L;

	@NotEmpty(message = "Comment content is required")
	@Size(min = 5, max = 65000)
	private String content;

	public CommentUpdateRequest(long comment_id, String content) {
		this.content = content;
	}
}
