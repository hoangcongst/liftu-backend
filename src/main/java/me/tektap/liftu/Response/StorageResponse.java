package me.tektap.liftu.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import me.tektap.liftu.entity.Comment;

public class StorageResponse extends BaseResponse {
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String data = null;

    public StorageResponse(int status, String data) {
        super(status);
        this.data = data;
    }
}
