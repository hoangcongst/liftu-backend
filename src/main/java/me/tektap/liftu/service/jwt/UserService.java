package me.tektap.liftu.service.jwt;

import me.tektap.liftu.Request.UserCreateRequest;
import me.tektap.liftu.entity.User;

public interface UserService {
    User create(UserCreateRequest userCreateRequest);
}
