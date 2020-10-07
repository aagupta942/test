package com.learning.microservices.limitservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LimitServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitServicesApplication.class, args);
	}

}
