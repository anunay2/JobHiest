package com.stackroute.profileindexing;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableCircuitBreaker
@EnableHystrixDashboard
@SpringBootApplication
@EnableRabbit
public class ProfileIndexingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileIndexingApplication.class, args);
	}

}
