package me.tektap.liftu.service;

import me.tektap.liftu.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    private UserRepository mUserRepository;

    @Autowired
    public UserServiceImpl(UserRepository mUserRepository) {
        this.mUserRepository = mUserRepository;
    }


}
