package me.tektap.liftu.Request;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostRequest implements Serializable {

	private static final long serialVersionUID = -5616176897013108345L;

	private String title;
	private String description;
	private String content;
	private String thumbnail;
	private byte status;

	public PostRequest(String title, String description, String content, String thumbnail, byte status) {
		this.title = title;
		this.description = description;
		this.content = content;
		this.status = status;
		this.thumbnail = thumbnail;
	}
}
