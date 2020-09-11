package com.flight;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan("com.flight.model")
@ComponentScan("com.flight.repository")
@ComponentScan("com.flight.service")
@ComponentScan("com.flight.controller")
public class FmsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FmsBackendApplication.class, args);
	}
}
