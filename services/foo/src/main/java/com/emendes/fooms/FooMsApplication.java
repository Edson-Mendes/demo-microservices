package com.emendes.fooms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FooMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FooMsApplication.class, args);
	}

}
