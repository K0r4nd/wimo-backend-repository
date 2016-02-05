package com.k0r4nd.wimo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.k0r4nd.wimo.api.model.Order;
import com.k0r4nd.wimo.ext.api.DhlServiceFacade;
import com.k0r4nd.wimo.ext.api.HermesServiceFacade;
import com.k0r4nd.wimo.ext.api.ShipperServiceFacade;

@Component
public class ShipperService {

	@Autowired
	private DhlServiceFacade dhlFacade;

	@Autowired
	private HermesServiceFacade hermesFacade;

	private List<ShipperServiceFacade> shippers;

	public Order findOrderByTrackingId(String trackingId) {
		Order order = null;
		for (ShipperServiceFacade shipperServiceFacade : shippers) {
			Order orderResponse = shipperServiceFacade.findByTrackingId(trackingId);
			if (orderResponse != null) {
				order = orderResponse;
				break;
			}
		}
		return order;
	}

	@PostConstruct
	void init() {
		shippers = new ArrayList<ShipperServiceFacade>();
		shippers.add(dhlFacade);
		shippers.add(hermesFacade);
	}

}
