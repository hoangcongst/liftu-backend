package me.tektap.liftu.controller;

import me.tektap.liftu.Request.UserUpdateRequest;
import me.tektap.liftu.Response.UserSecretInfoResponse;
import me.tektap.liftu.entity.User.User;
import me.tektap.liftu.entity.User.UserSecretInfo;
import me.tektap.liftu.service.jwt.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RepositoryRestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public ResponseEntity<?> show(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        try {
            UserSecretInfo info = this.userService.show(user.getId());
            return ResponseEntity.ok(new UserSecretInfoResponse(UserSecretInfoResponse.SUCCESS, info));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new UserSecretInfoResponse(UserSecretInfoResponse.FAILED, null));
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@ModelAttribute @Valid UserUpdateRequest userUpdateRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        UserSecretInfo savedUser = null;
        try {
            savedUser = this.userService.update(user.getId(), userUpdateRequest);
            return ResponseEntity.ok(new UserSecretInfoResponse(UserSecretInfoResponse.SUCCESS, savedUser));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new UserSecretInfoResponse(UserSecretInfoResponse.FAILED, null));
        }
    }
}
