package com.learning.microservices.limitservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.microservices.limitservices.bo.LimitConfiguration;
import com.learning.microservices.limitservices.util.Configuration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	Configuration configuration;

	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfiguration() {
		
		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}
	
	@GetMapping("/fault-tolerance")
	@HystrixCommand(fallbackMethod = "fallbackReferenceConfiguration")
	public LimitConfiguration getDefaultResponse() {
		
		throw new RuntimeException("Not available");
	}
	
	public LimitConfiguration fallbackReferenceConfiguration() {
		return new LimitConfiguration(999,9);
	}
}
