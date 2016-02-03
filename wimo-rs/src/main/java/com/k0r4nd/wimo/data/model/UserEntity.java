package com.k0r4nd.wimo.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public UserEntity() {

	}

	public UserEntity(User user) {
		this.password = user.getPassword();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.address = user.getAddress();
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
		this.password = password;
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

}
