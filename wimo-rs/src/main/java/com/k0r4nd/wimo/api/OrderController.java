package com.k0r4nd.wimo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.k0r4nd.wimo.api.model.Order;
import com.k0r4nd.wimo.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Order createOrder(@RequestBody Order order) {
		return orderService.save(order);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Order> getDummyOrder() {
		return orderService.findAllOrders();
	}
}
