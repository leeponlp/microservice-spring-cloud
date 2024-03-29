package com.leepon.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CopyUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(CopyUserApplication.class, args);
	}
}
