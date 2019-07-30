package com.stackroute.reviewerprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ReviewerProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewerProfileApplication.class, args);
	}

}
