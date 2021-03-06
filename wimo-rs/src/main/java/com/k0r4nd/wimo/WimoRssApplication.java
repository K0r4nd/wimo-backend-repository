package com.k0r4nd.wimo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class WimoRssApplication {

	public static void main(String[] args) {
		SpringApplication.run(WimoRssApplication.class, args);
	}
}
