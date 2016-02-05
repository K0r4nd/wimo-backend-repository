package com.k0r4nd.wimo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.k0r4nd.wimo.api.model.User;
import com.k0r4nd.wimo.api.model.exceptions.ForbiddenException;
import com.k0r4nd.wimo.api.model.exceptions.ResourceNotFoundException;
import com.k0r4nd.wimo.data.model.UserEntity;
import com.k0r4nd.wimo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}

	@Secured("ROLE_DEFAULT")
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public User updateUser(@RequestBody User user, @PathVariable String userId) {
		UserEntity userEntity = userService.findUserEntityById(userId);
		if (userEntity == null) {
			throw new ResourceNotFoundException("User was not found");
		} else {
			if (userEntity.getId().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
				return updateUser(userEntity, user);
			} else {
				throw new ForbiddenException("You're not allowed to access this resource!");
			}
		}
	}

	private User updateUser(UserEntity entity, User user) {
		entity.setAddress(user.getAddress() == null ? entity.getAddress() : user.getAddress());
		entity.setGcmToken(user.getGcmToken() == null ? entity.getGcmToken() : user.getGcmToken());
		entity.setName(user.getName() == null ? entity.getName() : user.getName());
		entity.setSurname(user.getSurname() == null ? entity.getSurname() : user.getSurname());
		return userService.save(entity);
	}
}
