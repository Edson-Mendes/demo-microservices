package com.emendes.barms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BarMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarMsApplication.class, args);
	}

}
