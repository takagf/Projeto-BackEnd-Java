package com.example.vendasms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VendasMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendasMsApplication.class, args);
	}

}
