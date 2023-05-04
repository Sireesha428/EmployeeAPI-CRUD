package com.last;

// Import necessary Spring Framework classes
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// Indicate that this is a Spring Boot application
@SpringBootApplication

// Enable service registration and discovery using Spring Cloud
@EnableDiscoveryClient
// Define the main class for the application
public class EmployeeApicrudApplication {

	// Define the main method that starts the Spring Boot application
	public static void main(String[] args) {
		SpringApplication.run(EmployeeApicrudApplication.class, args);
	}
}
