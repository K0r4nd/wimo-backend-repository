package com.k0r4nd.wimo.api.model;

import com.k0r4nd.wimo.config.ApplicationConfig;
import com.k0r4nd.wimo.data.model.UserEntity;

public class User {

	private String id;

	private String password;

	private String name;

	private String surname;

	private String address;
	
	private String gcmToken;
	
	public User(){
		this.password=ApplicationConfig.createPassword();
	}
	
	public User(UserEntity entity){
		this.id=entity.getId();
		this.name=entity.getName();
		this.surname=entity.getSurname();
		this.address=entity.getAddress();
		this.gcmToken=entity.getGcmToken();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password=ApplicationConfig.createPassword();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGcmToken() {
		return gcmToken;
	}

	public void setGcmToken(String gcmToken) {
		this.gcmToken = gcmToken;
	}
}
