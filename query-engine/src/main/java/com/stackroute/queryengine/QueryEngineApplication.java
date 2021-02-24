package com.stackroute.queryengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class QueryEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryEngineApplication.class, args);
	}

}
