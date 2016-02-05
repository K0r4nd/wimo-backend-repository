package com.k0r4nd.wimo.data.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k0r4nd.wimo.data.model.OrderEntity;
import com.k0r4nd.wimo.data.model.UserEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

	List<OrderEntity> findByUser(UserEntity user);
	
	List<OrderEntity> findByTrackingId(String trackingId);
}