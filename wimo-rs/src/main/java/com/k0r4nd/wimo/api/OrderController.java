package com.k0r4nd.wimo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.k0r4nd.wimo.api.model.Order;
import com.k0r4nd.wimo.api.model.exceptions.ResourceNotFoundException;
import com.k0r4nd.wimo.service.OrderService;
import com.k0r4nd.wimo.service.ScheduleService;
import com.k0r4nd.wimo.service.ShipperService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ShipperService shipperService;

	@Secured("ROLE_DEFAULT")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		order = shipperService.findOrderByTrackingId(order.getTrackingId());
		if (order == null) {
			throw new ResourceNotFoundException("Order with trackingId was not found");
		}
		Order newOrder = orderService.save(order);
		scheduleService.startJob(newOrder);
		return new ResponseEntity<Order>(newOrder, HttpStatus.CREATED);
	}

	@Secured("ROLE_DEFAULT")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Order> getDummyOrder() {
		return orderService.findAllOrders();
	}
}
