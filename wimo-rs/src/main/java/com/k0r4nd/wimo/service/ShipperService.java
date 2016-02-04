package com.k0r4nd.wimo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.k0r4nd.wimo.api.model.Order;
import com.k0r4nd.wimo.ext.api.DhlServiceFacade;
import com.k0r4nd.wimo.ext.api.HermesServiceFacade;
import com.k0r4nd.wimo.ext.api.ShipperServiceFacade;

@Component
public class ShipperService {
	
	private List<ShipperServiceFacade> shipperServices;
	
	public Order findOrderByTrackingId(String trackingId){
		Order order = null;
		for (ShipperServiceFacade shipperServiceFacade : shipperServices) {
			Order orderFound = shipperServiceFacade.findOrderByTrackingId(trackingId);
			if(orderFound!=null){
				order=orderFound;
				break;
			}
		}
		return order;
	}
	
	@PostConstruct
	void init(){
		shipperServices = new ArrayList<ShipperServiceFacade>();
		shipperServices.add(new DhlServiceFacade());
		shipperServices.add(new HermesServiceFacade());
	}

}
