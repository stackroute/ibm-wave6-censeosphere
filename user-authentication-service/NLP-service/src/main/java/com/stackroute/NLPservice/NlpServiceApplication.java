package com.stackroute.NLPservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NlpServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NlpServiceApplication.class, args);
	}

}
