package com.k0r4nd.wimo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.k0r4nd.wimo.api.model.User;
import com.k0r4nd.wimo.data.model.UserEntity;
import com.k0r4nd.wimo.data.repository.UserRepository;

@Component
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {
		UserEntity entity = new UserEntity(user);
		userRepository.save(entity);
		user.setId(entity.getId());
		return user;
	}

	public User findUserById(String id) {
		UserEntity entity = userRepository.findOne(id);
		return new User(entity);
	}

	public UserEntity findUserEntityById(String id) {
		return userRepository.findOne(id);
	}

	public User save(UserEntity entity) {
		userRepository.save(entity);
		User result = new User(entity);
		result.removePassword();
		return result;
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = findUserById(userId);
		List<GrantedAuthority> authorities = buildUserAuthorities();
		return buildUserForAuthentication(user, authorities);
	}

	private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
			List<GrantedAuthority> authorities) {

		return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), true, true,
				true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_DEFAULT"));
		return authorities;
	}

}
