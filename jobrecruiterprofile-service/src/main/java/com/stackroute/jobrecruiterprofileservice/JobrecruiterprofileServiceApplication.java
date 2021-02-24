package com.stackroute.jobrecruiterprofileservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableRabbit
@SpringBootApplication
public class JobrecruiterprofileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobrecruiterprofileServiceApplication.class, args);
	}

}
