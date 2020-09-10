package me.tektap.liftu.service.jwt;

import me.tektap.liftu.Request.UserCreateRequest;
import me.tektap.liftu.Request.UserUpdateRequest;
import me.tektap.liftu.entity.User.User;
import me.tektap.liftu.entity.User.UserSecretInfo;

public interface UserService {
    User create(UserCreateRequest userCreateRequest);
    UserSecretInfo show(long userId) throws Exception;
    UserSecretInfo update(long userId, UserUpdateRequest userCreateRequest) throws Exception;
}
