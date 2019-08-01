package com.stackroute.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WriteAReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(WriteAReviewApplication.class, args);
	}

}
