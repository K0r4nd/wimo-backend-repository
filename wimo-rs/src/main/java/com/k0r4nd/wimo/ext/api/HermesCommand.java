package com.k0r4nd.wimo.ext.api;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.k0r4nd.wimo.api.mock.MockController;
import com.k0r4nd.wimo.api.model.Order;
import com.k0r4nd.wimo.api.model.Shipper;
import com.k0r4nd.wimo.ext.api.model.HermesResponse;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HermesCommand extends HystrixCommand<HermesResponse> {

	private String trackingId;
	
	private RestTemplate template;
	
	private MockController mock;

	public HermesCommand(String trackingId, RestTemplate restTemplate, MockController mock) {
		super(HystrixCommandGroupKey.Factory.asKey("HERMES"));
		this.trackingId = trackingId;
		this.template=restTemplate;
		this.mock=mock;
	}

	@Override
	protected HermesResponse run() throws Exception {
		URI uri = new URI("http://localhost:8080/mock/hermes");
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(uri.toString()).queryParam("trackingId", trackingId);
		HermesResponse response =  template.getForObject(uriBuilder.build().encode().toUri(), HermesResponse.class);
		return response;
	}


	@Override
    protected HermesResponse getFallback() {
        return mock.getHermesByTrackingId(trackingId);
    }
	
}
