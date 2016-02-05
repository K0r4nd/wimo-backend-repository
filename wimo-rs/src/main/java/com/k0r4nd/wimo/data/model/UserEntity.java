package com.k0r4nd.wimo.data.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tomcat.util.security.MD5Encoder;

import com.k0r4nd.wimo.api.model.User;

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
		this.id = user.getId()==null? UUID.randomUUID().toString():user.getId();
		try {
			this.password = MessageDigest.getInstance("MD5").digest(user.getPassword().getBytes()).toString();
		} catch (NoSuchAlgorithmException e) {
			this.password=user.getPassword();
		}
		this.name = user.getName();
		this.surname = user.getSurname();
		this.address = user.getAddress();
		this.gcmToken=user.getGcmToken();
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
		try {
			this.password = MessageDigest.getInstance("MD5").digest(password.getBytes()).toString();
		} catch (NoSuchAlgorithmException e) {
			this.password=password;
		}
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
