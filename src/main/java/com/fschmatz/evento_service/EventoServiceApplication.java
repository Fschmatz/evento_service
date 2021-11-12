package com.fschmatz.evento_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EventoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventoServiceApplication.class, args);
	}

}
