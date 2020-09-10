package me.tektap.liftu.service.jwt;

import me.tektap.liftu.Request.UserCreateRequest;
import me.tektap.liftu.Request.UserUpdateRequest;
import me.tektap.liftu.dao.UserRepository;
import me.tektap.liftu.entity.User.User;
import me.tektap.liftu.entity.User.UserSecretInfo;
import me.tektap.liftu.service.AmazonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@Qualifier("jwtUserDetailsService")
public class JwtUserDetailsServiceImpl implements UserDetailsService, UserService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final UserRepository mUserRepository;
	private final AmazonClient amazonClient;
	public JwtUserDetailsServiceImpl(UserRepository mUserRepository, AmazonClient amazonClient) {
		this.mUserRepository = mUserRepository;
		this.amazonClient = amazonClient;
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
		User u = new User(userCreateRequest.getUsername(), userCreateRequest.getDisplay_name(),userCreateRequest.getEmail(),
				bCryptPasswordEncoder.encode(userCreateRequest.getPassword()), User.ACTIVE);
		User savedUser = this.mUserRepository.save(u);
		String avatarLink = this.amazonClient.uploadFile(userCreateRequest.getAvatar());
		savedUser.setAvatar(avatarLink);
		return this.mUserRepository.save(savedUser);
	}

	@Override
	public UserSecretInfo show(long userId) {
		return null;
	}

	@Override
	public UserSecretInfo update(long userId, UserUpdateRequest userCreateRequest) {
		return null;
	}
}
