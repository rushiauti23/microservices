package com.rushi.healthprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HealthProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthProviderApplication.class, args);
	}

}
