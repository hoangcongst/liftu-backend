package me.tektap.liftu.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class BaseResponse implements Serializable {
    public static final int SUCCESS = 0;
    public static final int FAILED = 1;
    protected final int status;
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message = null;

    public BaseResponse(int status) {
        this.status = status;
    }
}
