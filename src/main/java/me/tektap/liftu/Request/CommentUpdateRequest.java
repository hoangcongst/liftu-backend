package me.tektap.liftu.Request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentUpdateRequest implements Serializable {

	private static final long serialVersionUID = -5616176897013108345L;

	private String content;

	public CommentUpdateRequest(long comment_id, String content) {
		this.content = content;
	}
}
