package com.k0r4nd.wimo.ext.api;

import java.net.URI;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.k0r4nd.wimo.api.mock.MockController;
import com.k0r4nd.wimo.ext.api.model.DhlResponse;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class DhlCommand extends HystrixCommand<DhlResponse> {

	private String trackingId;

	private RestTemplate restTemplate;

	private MockController mock;

	public DhlCommand(String trackingId, RestTemplate restTemplate, MockController mock) {
		super(HystrixCommandGroupKey.Factory.asKey("DHL"));
		this.trackingId = trackingId;
		this.restTemplate = restTemplate;
		this.mock = mock;
	}

	@Override
	protected DhlResponse run() throws Exception {
		URI uri = new URI("http://localhost:8080/mock/dhl");
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(uri.toString()).queryParam("trackingId",
				trackingId);
		DhlResponse response = restTemplate.getForObject(uriBuilder.build().encode().toUri(), DhlResponse.class);
		return response;
	}

	@Override
	protected DhlResponse getFallback() {
		return mock.getDhlPackageByTrackingId(trackingId);
	}

}
