package com.k0r4nd.wimo.ext.api;

import com.k0r4nd.wimo.api.model.Order;

public interface ShipperServiceFacade {
	
	public Order findOrderByTrackingId(String trackingId);

}
