package me.tektap.liftu.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import me.tektap.liftu.entity.User.UserSecretInfo;

public class UserSecretInfoResponse extends BaseResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    private final UserSecretInfo data;

    public UserSecretInfoResponse(int status, UserSecretInfo user) {
        super(status);
        this.data = user;
    }
}