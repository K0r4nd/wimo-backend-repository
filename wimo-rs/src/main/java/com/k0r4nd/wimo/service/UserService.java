package com.k0r4nd.wimo.service;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.k0r4nd.wimo.api.model.User;
import com.k0r4nd.wimo.data.model.UserEntity;
import com.k0r4nd.wimo.data.repository.UserRepository;

@Component
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User createUser(User user){
		UserEntity entity = new UserEntity(user);
		userRepository.save(entity);
		user.setId(entity.getId());
		return user;
	}
	
}
