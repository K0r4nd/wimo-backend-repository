package com.k0r4nd.wimo.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k0r4nd.wimo.data.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
