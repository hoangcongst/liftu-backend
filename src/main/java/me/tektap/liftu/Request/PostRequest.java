package me.tektap.liftu.Request;

import lombok.Data;
import java.io.Serializable;

@Data
public class PostRequest implements Serializable {

	private static final long serialVersionUID = -5616176897013108345L;

	private String title;
	private String alias;
	private String content;
	private byte status;

	public PostRequest(String title, String alias, String content, byte status) {
		this.title = title;
		this.alias = alias;
		this.content = content;
		this.status = status;
	}
}
