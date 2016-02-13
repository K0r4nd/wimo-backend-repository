package com.k0r4nd.wimo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.k0r4nd.wimo.api.model.Order;
import com.k0r4nd.wimo.config.ApplicationConfig;
import com.k0r4nd.wimo.data.model.OrderEntity;
import com.k0r4nd.wimo.ext.api.model.GCMNotification;
import com.k0r4nd.wimo.ext.api.model.GCMPushMessage;

@Component
public class PushService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationConfig config;

	public void pushOrderUpdate(OrderEntity order) {
		GCMPushMessage message = createGCMPushMessage(order);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", config.getGcmApiKey());
		HttpEntity<GCMPushMessage> gcmEntity = new HttpEntity<GCMPushMessage>(message, headers);
		Object response = restTemplate.exchange(config.getGcmEndpoint(), HttpMethod.POST, gcmEntity, Object.class);
		System.out.println(response);
	}

	private GCMPushMessage createGCMPushMessage(OrderEntity order) {
		GCMNotification notification = new GCMNotification("New package status", order.getStatusText(), "myicon");
		Order newOrder = new Order(order);
		GCMPushMessage message = new GCMPushMessage(order.getUser().getGcmToken(), newOrder, notification);
		return message;
	}

}
