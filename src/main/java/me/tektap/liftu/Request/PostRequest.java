package me.tektap.liftu.Request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class PostRequest implements Serializable {
    private static final long serialVersionUID = -5616176897013108345L;

    @NotNull
    @Size(min = 10, max = 255)
    private String title;
    @NotNull
    @Size(max = 255)
    private String description;
    @NotNull
    @Size(min = 10, max = 65000)
    private String content;
    @NotNull
    @Size(max = 255)
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
