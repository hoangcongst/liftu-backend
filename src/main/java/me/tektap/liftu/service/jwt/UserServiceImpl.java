package me.tektap.liftu.service.jwt;

import me.tektap.liftu.Request.UserCreateRequest;
import me.tektap.liftu.Request.UserUpdateRequest;
import me.tektap.liftu.dao.UserRepository;
import me.tektap.liftu.entity.User.User;
import me.tektap.liftu.entity.User.UserSecretInfo;
import me.tektap.liftu.service.AmazonClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository mUserRepository;
    private final AmazonClient amazonClient;

    public UserServiceImpl(UserRepository mUserRepository, AmazonClient amazonClient) {
        this.mUserRepository = mUserRepository;
        this.amazonClient = amazonClient;
    }

    @Override
    public User create(UserCreateRequest userCreateRequest) {
        return null;
    }

    @Override
    public UserSecretInfo show(long userId) throws Exception {
        Optional<UserSecretInfo> result = this.mUserRepository.findUserById(userId, UserSecretInfo.class);
        if (result.isPresent())
            return result.get();
        else throw new Exception("User not found!");
    }

    @Override
    public UserSecretInfo update(long userId, UserUpdateRequest userUpdateRequest) throws Exception {
        Optional<User> result = this.mUserRepository.findUserById(userId, User.class);
        if (result.isPresent()) {
            User findUser = result.get();
            if (!findUser.getEmail().equals(userUpdateRequest.getEmail()))
                findUser.setEmail(userUpdateRequest.getEmail());

            if (!findUser.getDisplay_name().equals(userUpdateRequest.getDisplay_name()))
                findUser.setDisplay_name(userUpdateRequest.getDisplay_name());

            if (userUpdateRequest.getPassword() != null) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
                findUser.setPassword(bCryptPasswordEncoder.encode(userUpdateRequest.getPassword()));
            }

            if (userUpdateRequest.getAvatar() != null) {
                String avatarLink = this.amazonClient.uploadFile(userUpdateRequest.getAvatar());
                findUser.setAvatar(avatarLink);
            }

            User savedUser = this.mUserRepository.save(findUser);
            return this.show(savedUser.getId());
        } else
            throw new Exception("User not found!");
    }
}
