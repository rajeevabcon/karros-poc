package com.karros.poc.student.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication(scanBasePackages = {"com.karros.poc.student"})
public class StudentManagementServiceApplication {
	
	@Bean
	public WebClient getWebClient()
	{
		return WebClient.create();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(StudentManagementServiceApplication.class, args);
	}

}
