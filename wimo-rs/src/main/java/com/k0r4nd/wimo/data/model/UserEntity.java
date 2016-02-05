package com.k0r4nd.wimo.data.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.k0r4nd.wimo.api.model.User;
import com.k0r4nd.wimo.config.ApplicationConfig;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	private String id;

	private String password;

	private String name;

	private String surname;

	private String address;

	private String gcmToken;

	public UserEntity() {

	}

	public UserEntity(User user) {
		this.id = user.getId() == null ? UUID.randomUUID().toString() : user.getId();
		this.password = ApplicationConfig.passwordEncoder().encode(user.getPassword()).toString();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.address = user.getAddress();
		this.gcmToken = user.getGcmToken();
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
		this.password = ApplicationConfig.passwordEncoder().encode(password).toString();
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
