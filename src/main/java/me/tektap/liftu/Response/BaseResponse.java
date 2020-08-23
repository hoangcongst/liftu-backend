package me.tektap.liftu.Response;

import lombok.Data;
import java.io.Serializable;

@Data
public class BaseResponse implements Serializable {
    public static final int SUCCESS = 0;
    public static final int FAILED = 1;
    protected final int status;

    public BaseResponse(int status) {
        this.status = status;
    }
}
