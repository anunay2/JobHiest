package com.stackroute.jobindexing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@EnableCircuitBreaker
@EnableNeo4jRepositories
@SpringBootApplication
public class JobIndexingApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobIndexingApplication.class, args);
	}

}
