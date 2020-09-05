package me.tektap.liftu.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import me.tektap.liftu.entity.User;

import java.util.Date;

public class JwtTokenResponse extends BaseResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String token;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final User user;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Date exprired;

    public JwtTokenResponse(int status, String token, Date exprired, User user) {
        super(status);
        this.token = token;
        this.exprired = exprired;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public Date getExprired() {
        return exprired;
    }

    public User getUser() { return user; }
}