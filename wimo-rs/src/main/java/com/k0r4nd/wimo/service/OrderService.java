package com.k0r4nd.wimo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.k0r4nd.wimo.api.model.Order;
import com.k0r4nd.wimo.api.model.Shipper;
import com.k0r4nd.wimo.api.model.User;
import com.k0r4nd.wimo.data.model.OrderEntity;
import com.k0r4nd.wimo.data.model.UserEntity;
import com.k0r4nd.wimo.data.repository.OrderRepository;

@Repository
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	

	public Order save(Order order) {
		OrderEntity entity = new OrderEntity(order);
		entity = orderRepository.save(entity);
		order.setId(entity.getId());
		return order;
	}

	public List<Order> findOrdersByUser(User user) {
		UserEntity userEntity = new UserEntity(user);
		List<OrderEntity> orderEntities = orderRepository.findByUser(userEntity);

		return convertFromOrderEntitiesToOrders(orderEntities);
	}

	public List<Order> findAllOrders() {
		List<OrderEntity> orderEntities = orderRepository.findAll();
		return convertFromOrderEntitiesToOrders(orderEntities);
	}

	private List<Order> convertFromOrderEntitiesToOrders(List<OrderEntity> orderEntities) {
		List<Order> result = new ArrayList<Order>();

		for (OrderEntity orderEntity : orderEntities) {
			result.add(new Order(orderEntity));
		}
		return result;
	}

}
