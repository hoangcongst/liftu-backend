package me.tektap.liftu.service.jwt;

import me.tektap.liftu.Request.UserCreateRequest;
import me.tektap.liftu.dao.UserRepository;
import me.tektap.liftu.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class JwtUserDetailsService implements UserDetailsService, UserService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final UserRepository mUserRepository;
	public JwtUserDetailsService(UserRepository mUserRepository) {
		this.mUserRepository = mUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User mUser = this.mUserRepository.findUserByUsername(username);
		if (mUser == null) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return mUser;
	}

	public User create(UserCreateRequest userCreateRequest) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
		User u = new User(userCreateRequest.getUsername(), userCreateRequest.getDisplay_name(),
				userCreateRequest.getAvatar(), userCreateRequest.getEmail(),
				bCryptPasswordEncoder.encode(userCreateRequest.getPassword()), User.ACTIVE);
		return this.mUserRepository.save(u);
	}
}
