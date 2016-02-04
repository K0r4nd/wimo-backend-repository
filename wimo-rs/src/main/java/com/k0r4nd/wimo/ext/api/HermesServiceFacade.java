package com.k0r4nd.wimo.ext.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.k0r4nd.wimo.api.mock.MockController;
import com.k0r4nd.wimo.api.model.Order;
import com.k0r4nd.wimo.api.model.Shipper;
import com.k0r4nd.wimo.ext.api.model.HermesResponse;

@Component
public class HermesServiceFacade implements ShipperServiceFacade {

	@Autowired
	private MockController mock;
	
	@Autowired
	private RestTemplate template;
	
	@Override
	public Order findByTrackingId(String trackingId) {
		HermesCommand command = new HermesCommand(trackingId, template, mock);
		HermesResponse response = command.execute();
		if(response==null){
			return null;
		}else{
			return convertToOrder(response);
		}
	}


	private Order convertToOrder(HermesResponse response){
		Order order = new Order();
		order.setDeliveryDate(response.getDeliveryDate());
		order.setDeliveryState(response.getDeliveryState());
		order.setDeliveryStateText(response.getStatusText());
		order.setShippingDate(response.getShippingDate());
		order.setTrackingId(response.getTrackingId());
		order.setDestinationAddress(response.getDestinationAddress());
		order.setLastStatusUpdate(response.getLastStatusUpdate());
		order.setSentByUser(response.getSentByUser());
		order.setShipperName(Shipper.HERMES);
		order.setShippingDate(response.getShippingDate());
		return order;
	}
}
