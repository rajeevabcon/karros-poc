package com.karros.poc.service.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class StudentTransportServicediscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentTransportServicediscoveryApplication.class, args);
	}

}
