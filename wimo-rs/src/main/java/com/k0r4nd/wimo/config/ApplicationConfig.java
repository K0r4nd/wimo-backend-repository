package com.k0r4nd.wimo.config;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

	private static SecureRandom random = new SecureRandom();
	
	@Value("${wimo.quartz.push.interval}")
	private Integer scheduleInterval;
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public Integer getScheduleInterval() {
		return scheduleInterval;
	}
	
	public static String createPassword(){
		return new BigInteger(130,random).toString(32);
	}
}
