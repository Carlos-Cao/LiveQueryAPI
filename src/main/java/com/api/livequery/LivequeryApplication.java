package com.api.livequery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LivequeryApplication is the main entry point for the Spring Boot application.
 * It initializes the application context and starts the embedded server.
 */
@SpringBootApplication
public class LivequeryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivequeryApplication.class, args);
	}

}
