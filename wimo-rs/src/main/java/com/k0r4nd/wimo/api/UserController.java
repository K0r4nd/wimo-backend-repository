package com.k0r4nd.wimo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.k0r4nd.wimo.api.model.User;
import com.k0r4nd.wimo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public User createUser(@RequestBody User user){
		return userService.createUser(user);
	}
}
