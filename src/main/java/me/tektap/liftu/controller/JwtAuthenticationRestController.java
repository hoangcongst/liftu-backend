package me.tektap.liftu.controller;

import me.tektap.liftu.Request.UserCreateRequest;
import me.tektap.liftu.dao.UserRepository;
import me.tektap.liftu.entity.User.User;
import me.tektap.liftu.service.jwt.UserService;
import me.tektap.liftu.util.JwtTokenUtil;
import me.tektap.liftu.exception.AuthenticationException;
import me.tektap.liftu.Request.JwtTokenRequest;
import me.tektap.liftu.Response.JwtTokenResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

@Validated
@RepositoryRestController
@RequestMapping("/auth")
public class JwtAuthenticationRestController {

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService jwtUserDetailsService;
    private final UserRepository mUserRepository;
    private final UserService userService;

    public JwtAuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                                           @Qualifier("jwtUserDetailsService") UserDetailsService jwtUserDetailsService, UserRepository mUserRepository,
                                           @Qualifier("jwtUserDetailsService") UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.mUserRepository = mUserRepository;
        this.userService = userService;
    }

    @RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
            throws AuthenticationException {

        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            final User userDetails = mUserRepository
                    .findUserByUsername(authenticationRequest.getUsername());

            final String token = jwtTokenUtil.generateToken(userDetails);
            Date expired = jwtTokenUtil.getExpirationDateFromToken(token);
            return ResponseEntity.ok(new JwtTokenResponse(JwtTokenResponse.SUCCESS, token, expired, userDetails));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new JwtTokenResponse(JwtTokenResponse.FAILED, null, null, null));
        }
    }

    @RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = (User) jwtUserDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtTokenResponse(JwtTokenResponse.SUCCESS, refreshedToken, jwtTokenUtil.getExpirationDateFromToken(refreshedToken), user));
        } else {
            return ResponseEntity.badRequest().body(new JwtTokenResponse(JwtTokenResponse.FAILED, null, null, null));
        }
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@ModelAttribute @Valid UserCreateRequest userCreateRequest) {
        User user = this.userService.create(userCreateRequest);
        final String token = jwtTokenUtil.generateToken(user);
        Date expired = jwtTokenUtil.getExpirationDateFromToken(token);
        return ResponseEntity.ok(new JwtTokenResponse(JwtTokenResponse.SUCCESS,
                token, expired, user));
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }
    }
}
