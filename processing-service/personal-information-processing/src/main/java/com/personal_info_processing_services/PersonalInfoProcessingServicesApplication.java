package com.personal_info_processing_services;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class PersonalInfoProcessingServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalInfoProcessingServicesApplication.class, args);
	}

}
